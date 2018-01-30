package hz.com.hzdrag.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import hz.com.hzdrag.R;

/**
 * Created by hz on 2018/1/30.
 * GitHub：https://github.com/1428610664
 * #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG            #
 * #                                                   #
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<String> mData;
    private Context mContext;

    private OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener){
        mOnClickListener = onClickListener;
    }

    public MyAdapter(Context context, ArrayList<String> data) {
        this.mData = data;
        this.mContext = context;
    }

    public void updateData(ArrayList<String> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public void addData(ArrayList<String> data){
        this.mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rv_item, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // 绑定数据
        holder.mTv.setText(mData.get(position));
        holder.mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnClickListener != null){
                    mOnClickListener.onClick(v, position, mData);
                }
                //Toast.makeText(mContext, "点击：" + mData.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }

    public interface OnClickListener{
        void onClick(View v, int post, ArrayList<String> data);
    }
}