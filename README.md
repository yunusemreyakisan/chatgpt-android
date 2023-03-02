
# 🧠 ChatGPT ile Android Uygulaması

Bir bilgi verildiğinde, model bir veya daha fazla tahmini döndürür ve ayrıca her pozisyonda alternatif belirteçlerin olasılıklarını da döndürebilir.



### 🧩 Tamamlama Dökümanları
1 -  https://platform.openai.com/docs/guides/completion/introduction

2 - https://platform.openai.com/docs/api-reference/completions

### 🔗 API Kullanımı

#### GPT'ye sor

```http
  POST https://api.openai.com/v1/completions
```

**YOUR_TOKEN** yazan kısmı siteden oluşturduğunuz **key** ile değiştirin.

```kotlin
override fun getHeaders(): MutableMap<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["Content-Type"] = "application/json"
                    params["Authorization"] =
                        "Bearer YOUR_TOKEN"
                    return params
                }
```

### ⚙️ Kullanılan Teknolojiler

| **Teknoloji**           | **Entegre**                                                                |
| ----------------- | ------------------------------------------------------------------ |
| Volley | implementation 'com.android.volley:volley:1.2.1' |
| Lottie | implementation 'com.airbnb.android:lottie:3.4.4' |
  