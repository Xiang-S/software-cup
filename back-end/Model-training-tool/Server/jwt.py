import hmac
import json
import base64
import hashlib
import time
from typing import Dict
'''
以下代码中，encode_token方法用于生成JWT
接收两个参数：payload表示需要加密的数据
secret为服务器预设的密钥
decode_token方法用于解密JWT
接收两个参数：token表示需要解密的JWT字符串，secret同样为服务器预设的密钥。

在测试方法中，我们定义了一个Payload字典用于测试加密和解密方法，
使用encode_token方法生成JWT，然后使用decode_token方法解密JWT
最后输出解密后的Payload数据
注意，在实际使用时，需要将密钥进行保护，以免被黑客攻击和窃取。
'''
# 定义加密方法
def encode_token(payload: Dict, secret: str) -> str:
    # 设置Header和Payload
    header = {'alg': 'HS256', 'typ': 'JWT'}
    base64_header = base64.urlsafe_b64encode(json.dumps(header).encode('utf-8')).decode('utf-8').rstrip('=')
    base64_payload = base64.urlsafe_b64encode(json.dumps(payload).encode('utf-8')).decode('utf-8').rstrip('=')
    # 进行签名
    signature = hmac.new(secret.encode('utf-8'), f'{base64_header}.{base64_payload}'.encode('utf-8'), hashlib.sha256).digest()
    base64_signature = base64.urlsafe_b64encode(signature).decode('utf-8').rstrip('=')
    # 拼接JWT
    token = f'{base64_header}.{base64_payload}.{base64_signature}'
    return token

# 定义解密方法
def decode_token(token: str, secret: str) -> Dict:
    base64_header, base64_payload, base64_signature = token.split('.')
    # 验证签名
    signature = base64.urlsafe_b64decode(base64_signature + '=' * (-len(base64_signature) % 4))
    new_signature = hmac.new(secret.encode('utf-8'), f'{base64_header}.{base64_payload}'.encode('utf-8'), hashlib.sha256).digest()
    if signature != new_signature:
        raise ValueError('Invalid signature.')
    # 解码并返回Payload
    payload = base64.urlsafe_b64decode(base64_payload + '=' * (-len(base64_payload) % 4)).decode('utf-8')
    return json.loads(payload)

# 测试方法
payload = {'userId': 1234, 'exp': int(time.time()) + 3600}
secret = 'my_secret_key'
token = encode_token(payload, secret)
print("token:\n")
print(token,"\n")
decoded = decode_token(token, secret)
print(decoded)
