# sendgridjp-java-example

本コードは[SendGrid公式Javaライブラリ](https://github.com/sendgrid/sendgrid-java)の利用サンプルです。

## 使い方

```bash
git clone git@github.com:kikutaro/sendgridjp-java-example.git
cd sendgridjp-java-example
cp app\src\main\resources\.env.example cp app\src\main\resources\.env
# .envファイルを編集してください
gradle build
gradle run
```

## .envファイルの編集
.envファイルは以下のようになっています。

```bash
API_KEY=api_key
TOS=you@youremail.com,friend1@friendemail.com,friend2@friendemail.com
FROM=you@youremail.com
```
API_KEY:SendGridの[API Key](https://sendgrid.kke.co.jp/docs/User_Manual_JP/Settings/api_keys.html)を指定してください。  
TOS:宛先をカンマ区切りで指定してください。  
FROM:送信元アドレスを指定してください。  
