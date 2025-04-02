package sh.keygen.example;

import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.util.encoders.Hex;
import org.json.JSONObject;
import org.json.JSONException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.util.Base64;

public class Main {
  public static void main(String args[]) {
    String machineFile = "-----BEGIN MACHINE FILE-----\neyJlbmMiOiJKTEVhNCtKS3ZxUVdodUFyNnlFaHRzc3dwYlloc3ZqalBmUHFV\nRTZxYnpZOW1WTkpZYUxVVUdhZ0kvV2p2M0RhZkx4dVJLSXBBQjJFRVlLalRw\nTml6bEdOem5sazZ6WXgrUEtySXVESmhiTS92ZERMdTRXNksvSjFMQmV0Z3hD\nWHpaVXM4MitBYVg5QVdtdEswYlNGeXlsUFNrKzkzMXhGUnVTRWZsRnJiSkVG\nd0pNczhGOFdVd2QzRHNwN3ZlQ1hKREo5bVY3NC9ra04rMXQzdXdCNUZDWjhM\nLzNHcXljM0VhR1JYakZuQ09jbGdYZ0lkUlgyWjB6eUNvR0pFdUYwS2R6WlFB\nRVlPRmNVVjFqTXBwVThrNkRQeFVKRk9BTERKYUZUdWFIK1QyVDMzVm14WDJr\nZnV1UmJGZ2E5MjNCakYwcFlEMnZ1enVGR0NoUGFrRm5XQ0Mva0dYZVlZSTlp\nYUxncmVXdnBCVlpqRDF2TWowRmlvcStzM2ViWXdWendtalFrMitmbHJrdTJ3\nUVFjOFVBMUhlS0FRampNTnhJRUZNbkdpelhmY21RZmhyZWs4RkVwKzV2NS9L\nRGJ5MFJRek5pVjVENHlJMlhsOGR4bXVoL2ZhN3QrQTJKUjlZVTNnZXZWMVo5\nZ3RGNlFPSXpWTjFPN01MSHRwY2F1dG9vdGV2ZVc0dUFqcVpJdDB6aHZ1M212\nbWQzcDRpcW1oVHFFYXhUU254amxVcy9aS0ZpU0tQYllqNmM1YUN4YzFYTytK\nclJjSmlVZTVaaUFmTHR3MHluSVJpOVpTZjdzaGZLNVV0N29OMHY3L25hbms3\nZHdONUp4U1J3TXhVVy81OU5HQW5yaXI3cGRIZWdqZ1RRREVHZWdVbVkzSDdW\nK1U5eTF5TElzMzRXbGJvZHgrcWlnRFdoemludzdiVldYOEtXanc2SFBmc2xJ\nbVBRRmNoRm1ZTkJJc01qOWxmbkplNUVVUWlDbUM5VjZuTUo2aEpsekxsK1Jk\nUXJhaFZSRVlEUHNHWlZkUFh5VllRQ0Z6ZmlOUkVOb0hSaW55MCtwak1XMlhZ\nMWp4NzZHNVRUUW0vMzhucC9DZUtnMUhiNktMazV1dGRHV0JmU1pwdmlBeXov\ndEZISmJLYXRsQmdPM3FCdXM2dW5oNTRtSjNCQ2xzVkRWTmFqLzhVbUltVGdt\nMjUyNjIvTUQ3UmFWU3BSMjV6R2lCTDYvNWlOTUcrNVRXSGw3dy9WS3o4Z0xn\nNmNodjJhRnVJem80UU5FUzF1VThIOFFiVFdmN2YvSjhTYTZxdlUxbzA1VExR\nSHNMT081cWhnLzZCaVAwNGxRRVRiODQzMDNDNlhyUHNmNGZoODlQUklkSE51\nOEdUL2FSUXlhNExoSmdjQ2krT1dzOXNtVHhueVVPM2VDbWprdWhPOE5aT2Y4\nZUV2eldNREVQOGd4QjRXYUZVZk1GaGVMVWxZODRMdDBDZW1IcDdVWU8xT3A5\ncnE0KzJNSDgyb3o4WlBJZTJsUkJoTFdHdjNhTGM2RzN6QVZHM2tzMk1EaGgy\neDc4ZG5NV3hBTC9rZ3VLcVF2cG1rdmZrbGJMZVVicXE0VmNIUGYvMytGUzNM\nM1BKUUVwSE4ydDhLcFZEalpuaXZZM3o1OUZBdGIzWEhPNWtxTHNLRTc1Vkc2\nNFBDRmg4TFRPNVBra3lMRkQ0RXJIdjZBOWxGYzVYZ2RXUHRucGxaemwwKzNT\naWJOZDdPb1U0eGVGTkFJVW55b0tyTkFnUStHczk3Sk1iT2d3YlNtSHRaMC9X\nVnFHRGJBc1d6OFdjRDcvQW9WUXQ3aUd2NzlEUElzaFRIMUhOTnJIMUNVWklk\nVi84SXJ2clVQUGg4RWh4WWtIbnYxMXRuZU5HK0ZpWVgrcTZuYlJPNk1KclJK\nS3pCd0FlU3FxWWV6TkR2UFprUk4yUjRNSGhqT3l2MzNUQkUyUGMwY1Nlck9m\nWnFyaitaR1QxVTNzdDZEWGdESEhOSnJyaVlzbnlPU2crTkp4SUYzQ1VHNURN\nbWVFSlBTbVhqMGhIS2poYjVmcG5IR0FtZUQ3bnErdzhsaWF2bE16eE0vZGtn\nVWpZb0pNeExiVTdNR3Zad1lsNHE5NjM5RHhzbmU2RThWNWFEODk1ek1wdzMr\nQXB4MUNDR00xYXFPSWF1cG1WMGVxSkFXbjVHdFRybUFFa2VIajQ5N3BlRzZx\nZ1QyWCt4aVVCYXFwMUJmeE1SVE9LV1J4OTBPT2VTTng1R1BVakhRcGRTVFho\nTVBkN1FzdGV6LzdmQVpoNGpaanZLUzRldXFVUExnTnk3eXNQVUNMWktaQ1Ns\ncE1xWHllK0RIdDBVcDE2Q3FzRDc5dVJneXBEV2tFN1ZzUm0zV2dORE90MmFn\nSENtalBjQVRWTE8wWk5MZ1VZU1Jjd29BYU14VXRpbFJMeTRoc2RObWVQdUFX\nSW9YUDdXdk1XT3U3N0N0SzdZNUNTZkxuUjFHMFU4Rzdoci9pMVY1N0o3RFpF\nc00vakFaMEJacUo0bFZpMjFWU2VHaDlpMFJldksxYnEyakp5cHRGVUJlQWli\nU1pVVVdTMXdxbHJFSHNKbFNyNVgxRWdISzNqSnJhUC9sRGsycnh6VTFQcURu\ncSsrTkRWYTZCNkVGY0JGc3c3UlFYREtvbGdaZHJiNWFHSlVMN29ITXJPZUFo\nZXM5MUZkUFNlb0xjbGowUEpxL0w2SS8vcGhxenhxUGFweG9nRVBrK2FaY2lN\nTmwyQXU1ZCtGWXZrUWxaOHdDY1pSUEVkKzc2M3ZUanlGYWlnQVNTUHZmQmxK\nSE9JSXN2ZXVCRTJTb3hJeEEyNDNDTklTUFVCYTY0cVhyUzBOL0xLcXBVcDN4\ndHU2czZwbnJKT0p1WmxzN1FFK2FTaGQweS9Ibk1tSFdvNnQvd2ZoNDFySUFM\ncFVON3ZLdEJaZFJhRG91dUlsMWJyK0hCbm05Sm8zWmdOUmdDdFZ0T0hNTlVn\ndk9ZMldkWE40ckJXdk90ZTNERU5EbTBqTlNiYUVpMmtJMEtvYW14QTJka0NZ\nMTgrUjdDRzhuQWVLS3F6eHFOcDRMaWd2VlV6REx4bCtPb1Y2b1puTEJ6MFll\naWJCdGdscVhjcW1LaS9GdTljandlaEJTYmJ4VkZmK2h3RGkyaFR5YTNLbDNi\nU0JNSWdNajF1ZGJ1QnJ4Mmx6V3JYcTRVekxleDl5aGJhdVFwUFRTVlhWVnFG\na3pIZFNZOG1Mby8zTlFOZFJvclJuVzF1U2pMeHc2Nm01MGJOQXRlZ0xRcEpC\nbG1RVnVtTUtrTDR4MzM0WGVWT1ZwbndNQVhzTGMwdDVGTUJFdWVvLzhUZDdO\nWlZEOVNlQTVvOXZaajQwU0NJczRJR2FEODcvSEpJWTN4WmZKQ0pWQU5mUVBB\nMW9QTTEva2FsV1JuMnZRbVZ4R0VtZStyc01hejNBeXFiTkVXbkdIMVF1Vy9R\naXFYRkF2NERRQWFsMnRkZVhNblpRWDE5TUNOb1Fkb3k4WjJUbkVuZFNiNDhq\nTWJDdFZpb3BrdGlCcUhYOVZONVVkVEszQitNcTBYS01NV0o0THE5T2JPbkVS\nOUUvN0tLbFp4bnRWUC8zanBsVVVBQTVOOU5zR1lqT1ByTGJKTTVXdU8vMDFp\nNUt2TUlsYUovYWY4dHlJMVBlVjl2UW5acEthaGQ4NWVaK3V4ZHR5VkgwRzNu\neTVrSlN1RnJZVnFTbENkOWNjYXVYa0loU01uL3FuL3pPRjdFREswZkZZdjdQ\nZDNpZmwvc2ZhdEdDalowUk1sUmtveUU2M1U1N1U2cHFwT0xlS1I5bFUxelVQ\nZG40Q0p3b0lUWXYzL2cyOTFrY2RNSHc2b0hibVp4dGV5V2J1OTFvZWNUZzF5\nMXFzNGV4UzhXQjVramZUSjdyeFUzdXdtT2ZYQThuT3V5YWtFQWxiVE15RVN4\ncFBseFhsQ1hLb3EzZFVzRkxTNldUeko3WHJxek5rcDRGUm9nT1JvU1NmMDJu\nbFpCWUphY2RXeG9IWldsdFhMck0xUkcyUFpHYW1OcU50Vlh5KzBpMFVEa01R\nRDdzNFR2RGh6d1lKeUlSUlc4WXJrT1laWE1ZN25zVGh2L0d6TkhmNUU1V3Iv\neDRTQTg1aCtZbXU0YVRxbUpESXN2dEhYNnV6OTFQdElWc0VYNTU1eUZZNmty\nZjd4djRPcmNjL3BRYVFVY2UrRWZUL0tiQmNGcWwvbUFEemtGVUoxSW8xNitT\nd1o0azZYcWxqMm9uWDRMNStuZVU5VGJMTTdhZnhzaVFXWmY0UkFaZnk1UkdJ\nUTltUDd1aFFEdkh5RCs3Vk5Ddk5JU2pqRDRyZXVDMVpieDFVbTNlbWphWjE4\nOEsyYVhzYVBmZ2NrQll5MEV4TzVuU0tOZUVGdkVLOEVCaHVUQi9BMXBNOUY5\nT1Job0sxcnVyWWovNTN1Vm9ZOFc4NWVpVE5wdmEwUitKOUFaWGZtQm5kc25o\nRVZCdGJGTndxbzJDeUk0eDdWNTFEclNzdDVWcnplOW4yNzlGUldrbVJiV1Ew\nRjVIVTh1YlhiYk1FV0p3Z2RMaHUrWmcwbmxoYURPbi9TN1kvb2wwZGt3U1hM\nb1ZRSkhVMlpjMWJMWG5mQ1dQOHFKU25KYmVja243bEFxUzhzU3I2NXU1RitN\nekFkdGxuTGM2MG51ak5QZ0ZjU09kY0lpRExpVGlpRDdyTkYxZU1vYlMrbHlJ\nZGNYMEIxM1J3WmZ2MlZFOVZxRnBwMnZBWDcxOTlVSW1aN2VLZmxSMm9zT3V5\nbWkzWndSaVZySW1jbk5yQm0rWEpQMVZyWmQ1T1QycUkxdWRPbmg1NVF2dUJ2\nSEtTVThrL1hqRzVreEJtN0Z5Q1FUTzRvMldleGgySmtodFlkRERGblo3bjJN\naTNTdjd0eGJyMlJIWEw2VWJoSENKUzNlYXUrUzRHMU1FVVRzNlNjYk1DTmUr\ndEZkdzlxUnJoUUpjZVVKa1hQZzFXZk1IZ0k5SkE4RmpPK1h5N296RXF3QU01\ncXVuQmRjdjc4VU9zanYzc3BJSFdScjNtY2t4WnU3QVpDSTlqa29pcWJMMjg5\nMmxoUDhMUHJjcXNoZkdNNEhsY09qb2VXdXpaZk9wRWxTcENQOWJwQysyTngv\nUmZRZE9aUytGM3A3WEZuZzJSenFydDhDN1FDZ0FCcW94WDlqQVRQdEdnclZY\ndy8wWThnL1RGemZIeDdYUEN3OXc2S0dFOVU5REptU09aQ2xLb2lXUk5uSGF2\nVDdHR2EyTUI1bmhGSnFMbG9KdkM5TEJCbm9kTU1rUmFQSE9mdmJMempLaTlG\nNHQ5bjZwNnNWYUoyN1hTMmNMVnVzaVF6SzVUa0JSaHVSTlQrN3NSWDZzNXE0\nZmQzOVFoYXVzYmlzUXp4N25DWkt3STg4L0psQW96ZGpKbkpjMnhZSSs2dnlM\nSDdJVUc4cjNwdW9zSWtnTVFzeVMzSGxoemlrRzZWb001VVQ3S0luNFNKeWhI\nOHF6SGgrM3Nlc0tGazVUb0x0aVBPMDYyWTFjakNLMlBGRHhNa2pkWnJzM1pR\nTUR1MXBadUhmS29salZQRDJqT0dnRVlzU1huby93a2xjK3dxUTZBamhrRDlD\nTElRSjM4QW5tWEFCa0Z2ZFVUc2lHbXVNQ1Y2ZEJtVVFNQTJ2bW1Vc0FqT2l6\nMEtOL2s4VS9WNXhrUDUwb0xvQzdCUVo5QWxBTTJLeEJSRVRGeTluQ1FXNWgz\nY055WUwxUGg3VXkveWxpOFMxYjJTUmdwUjRBRHVSNEJpMjZFaGQwZUZVZEJD\nUFkwSE1FZjd4MGNxRmNpcHQ2cFBJUzg3ajNtRC9FeDVBYlFDVnl4Q0picmsy\nbUlnbGhaTHZQNEZ4a0RWTlpTUHdFa09lbFhBWkt1eUUxREkzQWQvaUxBMTNF\nNjI4OWVLWkRqK2ZJMlhXNGVxb3F5Q0JtRUV3dE5SS05wRDNHbzVWai8xTXBj\ncUZobi9INkszVlpPNUVtTG5idFZtb2cvdzB6b1g5cGhaZ1hvQ3NXdU42dWFW\neWpsQ25JQk8rRnBXam53NGdzTzZhZmFETTdmRUthbzFBNERER1lSbHVtM2JK\nMFk1TTQwSElleGVhM1Q0THFsYzhoQkh5TDdaMlltWFU3VlR5UVB6akhxTmlV\nUElyT3U4WTdkTElmOElQeHhDRzR1MldBUlJlOFVaaE9EVE9zUlQ0M0FKK0dV\nSHRJbTdWaTY2N1cwMnVaNGp2eWdESWhKK09pVy9rYWcwVlQzalAyYW9Ebk8v\nemQ4MFJPMVlxRGpqWFhrRUhHbzZTWTdsZSs5RnM5eFcyWmZMa1ViTjFKMTVp\nZTUrcGRIMjBFbEdKSXpwR1ZnOEM2VkZqcmJ4djBKMUdsQ0FxRDFzRyswck9k\nd1VyL1N6a0IzSUN6YWpMS3lpVlpsc3lhRVhmSFk1WEpRQ3dwWStuOU9BMUtu\nemNrZ3lzQ1JGTUdTTVRORUFRU0w5MkkyRXRGeWQ0TUlJTUo3ckxwK1VWbWZW\nSFpadmpITmZ6TldwaGhKTUJ1TnlwanorT2RlOGRnYnc5SnhuNkxKTmhnbDlw\nZlpEMVREUFdPbTRKRXl5ZER6czE0M0NtOFJubHNXRkVsSzNnN1F5d0cydlR1\nRGMybStrOURsRTY0bXBRS2kreDlxMUh3bGliRWtxbldZWWNPaThIbTg0RXlr\nQktuUTNhMUNGSG1MbUI2N0pLa1U1V0FCVDFDcTZ4Rmlzd0s2KzhvU1dmQUkv\nTzlKZEJNVU9UQjM2enpSYnROVktCSmVEUHQwMUtMeGY1c3I2cHhDd0NpWmg4\nQXhLMmNGQ0RuVFJLa3NWYktPTWRvTFlGNUVTN1RYN1lBbjZMRFNyUUIwMGoy\naXc5VGFuS2Y1YTg0dU1yQ0c0cVp5K1FCc1RpUWo3MDByQjUwdzdGdU1ISERI\nWksrSWNVY3NEU3VybWY5RFR6VFpUSzcyc2FEZ2tib0dTSnJ3eEo5NkRLRnJa\nbldxWG44OXEwamhTRlBRZC9xZS9saUFMMEJDMUJCbExwVmU2Z3k2R1FadmZQ\nUllaR3VmT2wzb3JndkMraEcwSGFIaitQZGZkN2VoNkFoYzY0cjRvckZ0cUdZ\nQVExdkh6Wm96TFFkNS9pa3Bsalg3ZGEwQWh0NXREcVZxazBwNXVtem1WMzhO\nNC8zVEUvNlFwdzJuUndNcjFLeGhQU1dZd1c0YkYrT0RjbWl6L0R4VWEyYzd3\ndUN4RmVQeDRqT29vNDl3dDFJV0VUVllRNWNzdHhTYm9uVVlpS2JVeXNpYVVC\nVkxreGxLQ2hlTVBSTmNoQ2NDeUV3Q2NzaEJ6VGZlajFaRkFQdEZHalhRSWJi\nQm1EcUVnTTg4dDRrSGZjTUcuMk5CQzhNd08veHlxM2xvTi5XWENBWU9OWEpS\nbmZreWVVY2MvQ2d3PT0iLCJzaWciOiIvckFWK3J3YWJUTFI0cG5URVJ2cFZ6\ndVpId3IrZWJXK3JIaFczdFp0blpZK0Fkdmk0SWY2N290d2U5eVVTUVpVYnVu\neUpUSCtBcitMek9sbUVhdk5Edz09IiwiYWxnIjoiYWVzLTI1Ni1nY20rZWQy\nNTUxOSJ9\n-----END MACHINE FILE-----\n";
    String machineFingerprint = "c4:af:69:1a:ee:55:f1:b5:a4:09:fd:74:d3:1f:94:f0";
    String licenseKey = "33362C-D254BA-F54C3C-DAAE48-C71975-V3";
    String publicKey = "e8601e48b69383ba520245fd07971e983d06d22c4257cfd82304601479cee788";

    // Parse signed machine file (removing cert header, newlines and footer)
    String encodedPayload = machineFile.replaceAll("(^-----BEGIN MACHINE FILE-----\\n|\\n|-----END MACHINE FILE-----\\n$)", "");
    byte[] payloadBytes = Base64.getDecoder().decode(encodedPayload);
    String payload = new String(payloadBytes);
    String encryptedData = "";
    String encodedSignature = "";
    String algorithm = "";

    try {
      JSONObject attrs = new JSONObject(payload);
      encryptedData = (String) attrs.get("enc");
      encodedSignature = (String) attrs.get("sig");
      algorithm = (String) attrs.get("alg");
    } catch (JSONException e) {
      System.out.println(
        String.format("Failed to parse machine file: %s", e.getMessage())
      );

      return;
    }

    // Verify machine file algorithm
    if (!algorithm.equals("aes-256-gcm+ed25519")) {
      System.out.println("Unsupported algorithm");

      return;
    }

    // Decode base64 signature and signing data to byte arrays
    byte[] signatureBytes = Base64.getDecoder().decode(encodedSignature);
    String signingData = String.format("machine/%s", encryptedData);
    byte[] signingDataBytes = signingData.getBytes();

    // Convert hex-encoded public key to a byte array
    byte[] publicKeyBytes = Hex.decode(publicKey);

    // Set up Ed25519 verifier
    Ed25519PublicKeyParameters verifierParams = new Ed25519PublicKeyParameters(publicKeyBytes, 0);
    Ed25519Signer verifier = new Ed25519Signer();

    verifier.init(false, verifierParams);
    verifier.update(signingDataBytes, 0, signingDataBytes.length);

    // Verify the signature
    boolean ok = verifier.verifySignature(signatureBytes);
    if (ok) {
      System.out.println("Machine file signature is valid!");

      // The decrypted plaintext dataset
      String plaintext = "";

      // Decrypt the machine file
      try {
        // Parse the encrypted data
        String encodedCiphertext = encryptedData.split("\\.", 3)[0];
        String encodedIv = encryptedData.split("\\.", 3)[1];
        String encodedTag = encryptedData.split("\\.", 3)[2];

        // Decode ciphertext, IV and tag to byte arrays
        byte[] ciphertext = Base64.getDecoder().decode(encodedCiphertext);
        byte[] iv = Base64.getDecoder().decode(encodedIv);
        byte[] tag = Base64.getDecoder().decode(encodedTag);
        byte[] key = new byte[256];

        // Hash license key with SHA-256 to obtain encryption key
        try {
          byte[] machineFingerprintBytes = machineFingerprint.getBytes();
          byte[] licenseKeyBytes = licenseKey.getBytes();
          MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

          sha256.update(licenseKeyBytes);
          sha256.update(machineFingerprintBytes);

          key = sha256.digest();
        } catch (NoSuchAlgorithmException e) {
          System.out.println(
            String.format("Failed to hash license key and machine fingerprint: %s", e.getMessage())
          );

          return;
        }

        // Set up AES-256-GCM
        AEADParameters cipherParams = new AEADParameters(new KeyParameter(key), 128, iv, null);
        GCMBlockCipher cipher = new GCMBlockCipher(new AESEngine());

        cipher.init(false, cipherParams);

        // Concat ciphertext and authentication tag to produce cipher input
        byte[] input = new byte[ciphertext.length + tag.length];

        System.arraycopy(ciphertext, 0, input, 0, ciphertext.length);
        System.arraycopy(tag, 0, input, ciphertext.length, tag.length);

        // Decrypt the ciphertext
        byte[] output = new byte[cipher.getOutputSize(input.length)];

        int len = cipher.processBytes(input, 0, input.length, output, 0);

        // Validate authentication tag
        cipher.doFinal(output, len);

        plaintext = new String(output);
      } catch (IllegalArgumentException | IllegalStateException | DataLengthException | InvalidCipherTextException e) {
        System.out.println(
          String.format("Failed to decrypt machine file: %s", e.getMessage())
        );

        return;
      }

      System.out.println("Machine file was successfully decrypted!");
      System.out.println(
        String.format("> Decrypted: %s", plaintext)
      );
    } else {
      System.out.println("Machine file signature is invalid!");
    }
  }
}
