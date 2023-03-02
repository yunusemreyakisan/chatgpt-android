package com.yakisan.chatgpt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.yakisan.chatgpt.R
import com.yakisan.chatgpt.model.Model

class MessageAdapter(private val messages: ArrayList<Model>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //VH's
    class UserMessageVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userMessage: TextView = itemView.findViewById(R.id.tvUserMessage)
    }

    class GPTMessageVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gptMessage: TextView = itemView.findViewById(R.id.tvGPTMessage)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return if (viewType == 0) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_message_item, parent, false)
            UserMessageVH(view)
        } else {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.gpt_message_item, parent, false)
            GPTMessageVH(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val sender = messages.get(position).sender
        when (sender) {
            "user" -> (holder as UserMessageVH).userMessage.setText(messages.get(position).message)
            "gpt" -> (holder as GPTMessageVH).gptMessage.setText(messages.get(position).message)
        }
    }


    override fun getItemCount(): Int {
        return messages.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (messages.get(position).sender) {
            "user" -> 0
            "gpt" -> 1
            else -> 1
        }
    }

}