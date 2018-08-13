package com.example.administrator.recyclerhorizontalscroll;

import android.content.Context;
import android.graphics.Color;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.recyclerhorizontalscroll.CustomHScrollView;
import com.example.administrator.recyclerhorizontalscroll.R;
import com.example.administrator.recyclerhorizontalscroll.entity;

import java.util.List;


/**
 * @author ZSK
 * @date 2018/8/9
 * @function
 */
public class TodayAccountAdapter extends
        RecyclerView.Adapter<TodayAccountAdapter.RegistrationObserverViewHolder> {

    private List<entity> data;
    private LinearLayout mHead;
    private Context mContext;
    private double n;
    private int type;

    private CustomHScrollView headSrcrollView;

    private OnItemClickListener itemClickListener;

    //点击标记位
    private int touchPosition = -1;

    public int getTouchPosition(){
        return touchPosition;
    }

    public void setTouchPosition(int touchPosition) {
        this.touchPosition = touchPosition;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public TodayAccountAdapter(Context context, List<entity> data, LinearLayout head) {
        this.data = data;
        this.mHead = head;
        this.mContext = context;
        notifyDataSetChanged();
    }

    public TodayAccountAdapter(Context context, List<entity> data, int type) {
        this.data = data;
        this.mContext = context;
        this.type = type;
        notifyDataSetChanged();
    }

    @Override
    public TodayAccountAdapter.RegistrationObserverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_4, parent, false);

        RegistrationObserverViewHolder viewHolder = new RegistrationObserverViewHolder(view);

        headSrcrollView = (CustomHScrollView) mHead.findViewById(R.id.h_scrollView);
        headSrcrollView.AddOnScrollChangedListener(new OnScrollChangedListenerImp(viewHolder.scrollView));

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public void onBindViewHolder(final TodayAccountAdapter.RegistrationObserverViewHolder holder, final int position) {
        if (holder == null) {
            return;
        }
        holder.textView1.setText(data.get(position).getName1());
        holder.textView2.setText(data.get(position).getName2());
        holder.textView3.setText(data.get(position).getName3());
        holder.textView4.setText(data.get(position).getName4());
        holder.textView5.setText(data.get(position).getName5());
        holder.textView6.setText(data.get(position).getName6());
        holder.textView7.setText(data.get(position).getName7());
        holder.textView8.setText(data.get(position).getName8());
        holder.textView9.setText(data.get(position).getName9());
        holder.textView10.setText(data.get(position).getName10());
        holder.textView11.setText(data.get(position).getName11());
        holder.textView12.setText(data.get(position).getName12());

        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                setTouchPosition(position);
                return false;
            }
        });
    }



    class OnScrollChangedListenerImp implements CustomHScrollView.OnScrollChangedListener {
        CustomHScrollView mScrollViewArg;

        public OnScrollChangedListenerImp(CustomHScrollView scrollViewar) {
            mScrollViewArg = scrollViewar;
        }

        @Override
        public void onScrollChanged(int l, int t, int oldl, int oldt) {
            mScrollViewArg.smoothScrollTo(l, t);
            if (n == 1) {//记录滚动的起始位置，避免因刷新数据引起错乱
                ((MainActivity)mContext).setPosData(oldl, oldt);
            }
            n++;
        }
    };

    class RegistrationObserverViewHolder extends RecyclerView.ViewHolder {
        private TextView textView1;
        private TextView textView2;
        private TextView textView3;
        private TextView textView4;
        private TextView textView5;
        private TextView textView6;
        private TextView textView7;
        private TextView textView8;
        private TextView textView9;
        private TextView textView10;
        private TextView textView11;
        private TextView textView12;
        private CustomHScrollView scrollView;

        public RegistrationObserverViewHolder(View itemView) {
            super(itemView);
            textView1 = (TextView) itemView.findViewById(R.id.textView_1);
            textView2 = (TextView) itemView.findViewById(R.id.textView_2);
            textView3 = (TextView) itemView.findViewById(R.id.textView_3);
            textView4 = (TextView) itemView.findViewById(R.id.textView_4);
            textView5 = (TextView) itemView.findViewById(R.id.textView_5);
            textView6 = (TextView) itemView.findViewById(R.id.textView_6);
            textView7 = (TextView) itemView.findViewById(R.id.textView_7);
            textView8 = (TextView) itemView.findViewById(R.id.textView_8);
            textView9 = (TextView) itemView.findViewById(R.id.textView_9);
            textView10 = (TextView) itemView.findViewById(R.id.textView_10);
            textView11 = (TextView) itemView.findViewById(R.id.textView_11);
            textView12 = (TextView) itemView.findViewById(R.id.textView_12);
            scrollView = (CustomHScrollView) itemView.findViewById(R.id.h_scrollView);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
