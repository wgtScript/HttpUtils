package com.wgt.rsa;

import java.nio.charset.Charset;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.codec.Base64Encoder;

public class RSAUtil {

	/**
	 * 默认算法密钥
	 */
	private static final byte[] ENCRYPT_KEY = { -81, 0, 105, 7, -32, 26, -49, 88 };

	public static final String CHARSET = "UTF-8";

	/**
	 * BASE64解码
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static final byte[] decryptBASE64(String key) {
		try {
			return Base64Decoder.decode(key);
		} catch (Exception e) {
			throw new RuntimeException("解密错误，错误信息：", e);
		}
	}

	/**
	 * BASE64编码
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static final String encryptBASE64(byte[] key) {
		try {
			return Base64Encoder.encode(key);
		} catch (Exception e) {
			throw new RuntimeException("加密错误，错误信息：", e);
		}
	}

	/**
	 * 数据加密，算法（RSA） * 公钥加密，私钥解密
	 * 
	 * @param data
	 *            数据
	 * @return 加密后的数据
	 */
	public static final String encryptRSAPublic(String data, String publicKey) {
		try {
			return encryptBASE64(RSACoder.encryptByPublicKey(data.getBytes(CHARSET), decryptBASE64(publicKey)));
		} catch (Exception e) {
			throw new RuntimeException("解密错误，错误信息：", e);
		}
	}

	/**
	 * 数据解密，算法（RSA） 公钥加密，私钥解密
	 * 
	 * @param cryptData
	 *            加密数据
	 * @return 解密后的数据
	 */
	public static final String decryptRSAPrivate(String cryptData, String privateKey) {
		try {
			// 把字符串解码为字节数组，并解密
			return new String(RSACoder.decryptByPrivateKey(decryptBASE64(cryptData), decryptBASE64(privateKey)),
					Charset.forName("utf-8"));
		} catch (Exception e) {
			throw new RuntimeException("解密错误，错误信息：", e);
		}
	}

	public static void main(String[] args) {
		String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMVu7IeM3j7Xw6NF"
				+ "LLM5SWLUhzS0P9mR+gN4cdMPdLq/+xWd9zOlp+rPt81dQULI7uYmUznwgAegca+u"
				+ "OEJezkTb3JIjrZCTm0L2DWh+sNyn0MtLcewFlV/CpWWC1M9XrghRIxyQ/1k+tp0C"
				+ "jJRjmgZcmGQc/4wARodohhpKWCaJAgMBAAECgYA5fQockjUooVUTFpJkgXrP70iL"
				+ "XSz2Yl4lYo3qQibgNgSbj8qqXEMfyWogv1XAZ5fApko3pcLx9ZME77rsAIUY3oes"
				+ "Mn8UelSFFLrkersTaUq5PdbAbj7xqOurN5+rQvVoQPB20z33RGgTS24ooZC+kT42"
				+ "noaMFPSqm/8cSHL48QJBAPLRNPXRCnlX1S3rlfPVYaPU7ew2gQprUlHNVUS9/3T0"
				+ "DGIBczkD5wxvAzfOr1fDP2M97w9gOXbpi+XrFcNxmyUCQQDQJvL0zakHq40aReSM"
				+ "7eJVZe49mb/HIrPKJM8s61lGRVZOjoct7cGAWdKLXOOx7RJCd+6Y7B2YCu2hTZGU"
				+ "O1KVAkAlMeTR7taS5eBy606KDlqplPlpIKQ9q1jtzO1N3WjfSm8HKD+1MSLYZbzx"
				+ "dIipgfAYzKjtCwlibL4QVyV7TdC9AkB5b1+qhhkoymKlg0UVGutCWxckhQAnUw2E"
				+ "zvi3Ag6xOl5hsjEXEHAlS0HT4DtOYDqY4JMoUAfr3eRIiJJ34iwJAkEA7Bqxvc6Z"
				+ "6Ad6qpjOHusah5lbleKedEeoMlWggnkvD6G0RjWNTBtQV2yzePN/Ez99RIsS8tZX" + "OUeP5E6nwvKPCA==";
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFbuyHjN4+18OjRSyzOUli1Ic0"
				+ "tD/ZkfoDeHHTD3S6v/sVnfczpafqz7fNXUFCyO7mJlM58IAHoHGvrjhCXs5E29yS"
				+ "I62Qk5tC9g1ofrDcp9DLS3HsBZVfwqVlgtTPV64IUSMckP9ZPradAoyUY5oGXJhk" + "HP+MAEaHaIYaSlgmiQIDAQAB";
		System.out.println("privateKey:" + privateKey);
		System.out.println("publicKey:" + publicKey);

		String encryptpub = encryptRSAPublic("b04df9229727e1a970404f9d8481dfb32018-07-17 16:53:22", publicKey);
		// encryptpub="rg2iUxyaQIKEdyp9cbXs4lMg5LIdM4jVx9XZ+ApfK+oTqODlMwQutOe0HIQS1bqvw6KAqC7ofUw5SSQ02p4uSJfIEkEMLVEtIrLXOon7pOawPRDwA4XtQuZspELayejEVrBoGc2TUrmRD/K81lGdP6WXXITnm2oZ3aH5OS7DaL4=";
		//encryptpub = "kBrCP1M/6fdKJFBEOzElSJSR9OR5hPa4swMj1hHVvT2KVfP6xtUoX9RbMK1+MjrwB9Mxvc53fuJ6pFrczVGxyGE9Owet7dy1cEhJdQtHqPc0usc+t2hxDlU9ylTgx+9vyLUAPqj/DQ9BGN+/IBne6MCgD9AHRtJOIOPaC6CJF+w=";
		encryptpub="or+DpFJEawII8JcuZtGJ3gRz2xcduHpoW1VT2uDm1GDn3EuqgInIqAJWpKsyIZxjElcWiK+WdDPQRGvQK1zdSkTAr3l0YL+Rm1JY54q8V2RozhRZplvFO9+ggOCl64Urxb0PkEIElCuJvdWqc5V+WOVGL9jVJBKoTLErtKR8Fgg=";
		String org = decryptRSAPrivate(encryptpub, privateKey);
		System.out.println("org==" + org);
	}

}
