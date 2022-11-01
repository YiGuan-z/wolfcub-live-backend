package com.cqsd.utils;

import com.cqsd.vo.LoginInfo;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * token 管理类
 * TODO 超时管理机制
 */
abstract public class TokenManager {
	
	public static final String TOKEN_NAME = "X-Token";
	
	// ConcurrentHashMap 保障共享资源线程安全问题
	private static final Map<String, LoginInfo> TOKEN_MAP = new ConcurrentHashMap<>();
	
	/**
	 * create user Token
	 *
	 * @return Token
	 */
	
	public static String getToken() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * add userInfo
	 *
	 * @param token    token
	 * @param userInfo userInfo
	 */
	public static void addInfo(String token, LoginInfo userInfo) {
		TOKEN_MAP.put(token, userInfo);
	}
	
	/**
	 * Get map size
	 *
	 * @return size
	 */
	public static Integer getSize() {
		return TokenManager.TOKEN_MAP.size();
	}
	
	/**
	 * Check for user
	 * Use parallel objects  will get a higher speed.
	 *
	 * @param username username
	 */
	public static boolean logged(String username) {
		Objects.requireNonNull(username, "检查的用户名不能为空");
		final var count = TOKEN_MAP.values()
				.parallelStream()
				.filter(user -> Objects.equals(user.getUsername(), username))
				.count();
		return count > 0;
//		Map<String, LoginInfo> tokenMap = TokenManager.TOKEN_MAP;
//		Set<String> keySet = tokenMap.keySet();
//		for (String key : keySet) {
//			LoginInfo loginInfo = tokenMap.get(key);
//			if (loginInfo.getUsername().equals(username)) {
//				throw new RuntimeException("用户已登录！！！");
//			}
//		}
	}
	
	/**
	 * @param token userToken
	 * @return LoginInfo Object
	 */
	public static LoginInfo getInfo(String token) {
		Objects.requireNonNull(token,"token Invalid");
		return TOKEN_MAP.get(token);
	}
	
	public static LoginInfo removeInfo(String token) {
		return TOKEN_MAP.remove(token);
	}
}
