package com.fgw.project.service.msghttp.demo;

import com.fgw.project.util.msg.RSA;
import com.fgw.project.util.msg.RSA.RSAKey;

public class GenerateRsaKey {

	public static void main(String[] args) throws Exception {
		RSAKey key = RSA.generateRSAKey();
		System.out.println("publicKey: " + key.getPublicKeyBase64String());
		System.out.println("privateKey: " + key.getPrivateKeyBase64String());
	}
}
