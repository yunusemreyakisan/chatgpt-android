package com.yakisan.chatgpt.ui

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.OnLayoutChangeListener
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.RetryPolicy
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.yakisan.chatgpt.adapter.MessageAdapter
import com.yakisan.chatgpt.databinding.ActivityChatBinding
import com.yakisan.chatgpt.model.Model
import org.json.JSONObject


//TODO: API Key icin README dosyası olustur, aciklamalarini yaz.

lateinit var message_adapter: MessageAdapter
lateinit var messages: ArrayList<Model>
val url = "https://api.openai.com/v1/completions"

class Chat : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Init RecyclerView
        messages = ArrayList()
        message_adapter = MessageAdapter(messages)
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.adapter = message_adapter


        //OnClickListener
        binding.animationView.setOnClickListener {
            hideKeyboardFrom(applicationContext, it)
            binding.animationView.animate()
            if (binding.txtChat.text.toString().length > 0) {
                messages.add(Model(binding.txtChat.text.toString(), "user"))
                message_adapter.notifyDataSetChanged()
                getResponse(binding.txtChat.text.toString().trim())
            } else {
                runOnUiThread {
                    val toast = Toast.makeText(
                        applicationContext,
                        "Mesaj giriniz",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
            }
        }


    }

    //Response
    private fun getResponse(question: String) {
        binding.txtChat.setText("")
        val queue: RequestQueue = Volley.newRequestQueue(applicationContext)
        val jsonObject: JSONObject? = JSONObject()
        jsonObject?.put("model", "text-davinci-003")
        jsonObject?.put("prompt", question)
        jsonObject?.put("max_tokens", 4000)
        jsonObject?.put("temperature", 0)

        val postRequest: JsonObjectRequest =
            object : JsonObjectRequest(Method.POST, url, jsonObject, Response.Listener { response ->
                val resMessage: String =
                    response.getJSONArray("choices").getJSONObject(0).getString("text").trim()
                messages.add(Model(resMessage, "gpt"))
                message_adapter.notifyDataSetChanged()
            }, Response.ErrorListener {
                Toast.makeText(applicationContext, "Failed response", Toast.LENGTH_SHORT)
                    .show()
            }) {
                override fun getHeaders(): MutableMap<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["Content-Type"] = "application/json"
                    params["Authorization"] =
                        "Bearer YOUR_TOKEN"
                    return params
                }
            }

        postRequest.setRetryPolicy(object : RetryPolicy {
            override fun getCurrentTimeout(): Int {
                return 50000
            }

            override fun getCurrentRetryCount(): Int {
                return 50000
            }

            override fun retry(error: VolleyError?) {
                runOnUiThread {
                    val toast = Toast.makeText(
                        applicationContext,
                        "API Hatası",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
            }
        })
        queue.add(postRequest)


    }

    //Hide Keyboard Func.
    fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}

