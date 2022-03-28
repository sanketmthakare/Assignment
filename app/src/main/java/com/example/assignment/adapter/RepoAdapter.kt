package com.example.assignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment.R
import com.example.assignment.data.Item
import com.example.assignment.data.TrendingRepoModel
import de.hdodenhof.circleimageview.CircleImageView

class RepoAdapter(var mItemClickListener: ItemClickListener) :
    RecyclerView.Adapter<RepoAdapter.RepoHolder>() {

    var repoList = mutableListOf<Item>()

    interface ItemClickListener {
        fun onItemClick(ownerName: String, repoName: String)
    }

    fun setApiRepoList(repos: List<Item>) {
        this.repoList = repos.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
        return RepoHolder(view);
    }

    override fun onBindViewHolder(holder: RepoHolder, position: Int) {
        val item: Item = repoList.get(position)
        holder.userId.setText("User Id :" + (item.owner.id).toString())
        holder.userName.setText("User Name :" + item.name)
        Glide.with(holder.itemView.context).load(item.owner.avatar_url).into(holder.userImage)

        holder.itemView.setOnClickListener(View.OnClickListener { view ->
            mItemClickListener.onItemClick(
                repoList.get(position).owner.login,
                repoList.get(position).name
            )
        })
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    class RepoHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var userId: TextView = itemView.findViewById(R.id.user_id)
        var userName: TextView = itemView.findViewById(R.id.user_name)
        var userImage: CircleImageView = itemView.findViewById(R.id.user_image)
    }

}