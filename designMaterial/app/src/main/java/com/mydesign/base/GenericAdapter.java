package com.mydesign.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericAdapter<T, V extends ViewDataBinding> extends RecyclerView.Adapter<GenericViewHolder> {

    private List<T> listItems;
    private LayoutInflater mInflater;

    public GenericAdapter(Context _context) {
        this.listItems = new ArrayList<>();
        mInflater = LayoutInflater.from(_context);
    }

    public void updateData(ArrayList<T> _listItems) {
        listItems = _listItems;
        notifyDataSetChanged();
    }

    public List<T> getList(){
        return listItems;
    }


    @NonNull
    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GenericViewHolder<V>(DataBindingUtil.inflate(mInflater, getLayoutId(), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        onBindView((V) holder.mBinding, listItems.get(position), position);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public abstract int getLayoutId();

    public abstract void onBindView(V binding, T item, int position);
}
