
# 🧠 Android App with ChatGPT

Given a prompt, the model will return one or more predicted completions, and can also return the probabilities of alternative tokens at each position.



### 🧩 Completion Docs
1 -  https://platform.openai.com/docs/guides/completion/introduction

2 - https://platform.openai.com/docs/api-reference/completions

### 🔗 API Usage

#### Ask GPT

```http
  POST https://api.openai.com/v1/completions
```

Replace **YOUR_TOKEN** with the **key** you created from the site.

```kotlin
override fun getHeaders(): MutableMap<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["Content-Type"] = "application/json"
                    params["Authorization"] =
                        "Bearer YOUR_TOKEN"
                    return params
                }
```

That's the **magic!**

### 🧪 Potions Used

| **Libraries**           | **Implementation**                                                                |
| ----------------- | ------------------------------------------------------------------ |
| Volley | implementation 'com.android.volley:volley:1.2.1' |
| Lottie | implementation 'com.airbnb.android:lottie:3.4.4' |

### ▶️ Demo
https://user-images.githubusercontent.com/116274664/222902988-5f4d0d58-d91b-4a12-be27-1b7925a1d002.mp4