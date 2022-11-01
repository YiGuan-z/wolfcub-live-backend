package com.cqsd.socket.core.utils;


import com.cqsd.data.entry.BulletMsgSensitive;
import com.cqsd.socket.core.consts.BarrageCacheKeyConst;
import com.cqsd.socket.core.consts.BarrageMsgSensitiveUtilsConst;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class BarrageMsgSensitiveUtils {

    private static List<BulletMsgSensitive> msgSensitives = new ArrayList<>(256);

    public static void setSensitiveWords(List<BulletMsgSensitive> msgSensitives) {
        BarrageMsgSensitiveUtils.msgSensitives = msgSensitives;
        initSensitiveMsgMap();
    }

    private static final Map<Object, Object> sensitiveWordMap = new HashMap<>(256);

    private static void initSensitiveMsgMap() {
        Map<Object, Object> tempMap;
        Map<Object, Object> newMsgMap;
        for (BulletMsgSensitive word : msgSensitives) {
            tempMap = sensitiveWordMap;
            String key = word.getSensitiveMsg();
            for (int i = 0; i < key.length(); i++) {
                char keyChar = key.charAt(i);
                Object wordMap = tempMap.get(keyChar);
                //如果存在该key,直接赋值,用于下一个循环获取
                if (wordMap != null) {
                    tempMap = (Map) wordMap;
                } else {
                    newMsgMap = new HashMap<>(2);
                    newMsgMap.put(BarrageMsgSensitiveUtilsConst.SENSITIVE_MSG_END_FLAG, false);
                    tempMap.put(keyChar, newMsgMap);
                    tempMap = newMsgMap;
                }
                if (i == key.length() - 1) {
                    tempMap.put(BarrageMsgSensitiveUtilsConst.SENSITIVE_MSG_END_FLAG, true);
                }
            }
        }
    }


    public static boolean contains(String txt) {
        boolean flag = false;
        for (int i = 0; i < txt.length(); i++) {
            int matchFlag = checkSensitiveMSg(txt, i);
            if (matchFlag > 0) {
                flag = true;
            }
        }
        return flag;
    }


    private static int checkSensitiveMSg(String txt, int beginIndex) {
        boolean flag = false;
        int matchFlag = 0;
        char word;
        Map nowMap = sensitiveWordMap;
        for (int i = beginIndex; i < txt.length(); i++) {
            word = txt.charAt(i);
            nowMap = (Map) nowMap.get(word);
            if (nowMap != null) {
                matchFlag++;
                if ((Boolean) nowMap.get(BarrageMsgSensitiveUtilsConst.SENSITIVE_MSG_END_FLAG)) {
                    flag = true;
                }
            } else {
                break;
            }
        }
        if (matchFlag < 2 || !flag) {
            return -1;
        }
        return matchFlag;
    }


    public static List<String> getSensitiveMsg(String txt) {
        List<String> sensitiveWordList = new ArrayList<>();
        for (int i = 0; i < txt.length(); i++) {
            int length = checkSensitiveMSg(txt, i);
            if (length > 0) {
                sensitiveWordList.add(txt.substring(i, i + length));
                i = i + length - 1;
            }
        }
        return sensitiveWordList;
    }

    public static String replaceSensitiveMsg(String msg) {
        List<String> sensitiveMsgList = getSensitiveMsg(msg);
        AtomicReference<String> retMsg = new AtomicReference<>(msg);
        sensitiveMsgList.forEach(sensitiveMsg -> {
            String showMsg = BarrageCacheUtils.hashGet(BarrageCacheKeyConst.BARRAGE_MSG_SENSITIVE_KEY, sensitiveMsg);
            retMsg.set(retMsg.get().replace(sensitiveMsg, StringUtils.isBlank(showMsg) ? sensitiveMsg :showMsg));
        });
        return retMsg.get();
    }

}
