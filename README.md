# ITArchitecture2019課題

## 演習：オリジナルなAdnroidサービスを作る
### 以下の条件を満たすサービスを実装する
- 実装方法やサービス・AIDLの詳細は，各自で予習
- アプリ外からアクセス可とすること
  - AIDLを使うことが目的
- 実装するサービスが提供する機能は，各自オリジナルなものを考えること
  - 昨年度までの履修者作成のものと被らない機能を考案
  - サービスとしての有効性は問わない
- エミュレータで動作すること
- サービス機能を呼び出す側のアプリも作成すること
- (可能であれば)GitHubでプロジェクトのコードを管理すること

# 作成サービス
### アプリ名を入力することで，該当アプリを起動させるサービス
- 現状では以下のキーワードで該当アプリが起動します
  - `chrome`，`設定`，`youtube`，`マップ`，`パズドラ`，`モンスト`，`ミュージック`，`カレンダー`，`メール`，`twitter`
- `com/example/remoteservice/ApplicationList.kt`内のAppListに`{アプリ名} to arrayOf({パッケージ名}, {クラス名})`で新たにアプリを追加可能
  - 例："chrome" to arrayOf("com.android.chrome", "com.google.android.apps.chrome.Main")
  - アプリ名は入力する文字を入力してください
  - パッケージ名とクラス名は，調べるor本アプリ内でLog出力している部分を元に入力してください  
  ※ Log出力は一度，本アプリ内の`アプリ起動`ボタンを押すことで，インストールされているアプリのパッケージ名とクラス名が表示されます．起動可能なパッケージと起動不可能なパッケージがあるため，注意が必要です

# 参考サイト
### AIDL関連
- Androidインターフェース定義言語(AIDL), https://developer.android.com/guide/components/aidl
- commonsguy/cw-advandroid, https://github.com/commonsguy/cw-advandroid/tree/master/AdvServices

### 外部アプリ起動関連
- Android開発 他のアプリを立ち上げるための方法, https://qiita.com/xu1718191411/items/25faefe055ebb315d041
