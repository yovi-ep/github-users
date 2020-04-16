package yovi.putra.github.features

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_of_user_list.view.*
import yovi.putra.github.R
import yovi.putra.github.core.utils.ui.load
import yovi.putra.github.data.model.User

class MainAdapter : RecyclerView.Adapter<MainAdapter.VHolder>() {

    private var item = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder =
        VHolder(
            LayoutInflater
                .from(parent.context).inflate(
                    R.layout.item_of_user_list,
                    parent,
                    false
                )
        )

    fun setItem(data: MutableList<User>?) {
        data?.let {
            item.clear()
            item.addAll(it)
            this.notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: VHolder, position: Int) =
        holder.binding(item[position])

    class VHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun binding(data: User) {
            containerView.apply {
                tv_name.text = data.login
                img_avatar.load(context, data.avatar_url, rounded = true)
            }
        }
    }
}