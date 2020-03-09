package com.midnightgeek.falldetect;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.recyclerview.widget.RecyclerView;

import com.midnightgeek.falldetect.databinding.ItemFallsBinding;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<ModelFall> date=new ArrayList<>();
    private LifecycleOwner mLifecycleOwner;
    private ListViewModel listViewModel;

    public Adapter(LifecycleOwner mLifecycleOwner, ListViewModel listViewModel) {
        this.mLifecycleOwner = mLifecycleOwner;
        this.listViewModel = listViewModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFallsBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_falls, parent, false);
        return new FallViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ModelFall currentStudent = date.get(position);
        ((FallViewHolder)holder).binder.setModel(currentStudent);

    }

    @Override
    public int getItemCount() {
        if (date != null) {
            return date.size();
        } else {
            return 0;
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ((FallViewHolder)holder).markAttach();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        ((FallViewHolder)holder).markDetach();
    }

    class FallViewHolder extends RecyclerView.ViewHolder implements LifecycleOwner{
        public ItemFallsBinding binder;
        private LifecycleRegistry mLifecycleRegistry;

        public FallViewHolder(@NonNull ItemFallsBinding bindeing) {
            super(bindeing.getRoot());
            mLifecycleRegistry = new LifecycleRegistry(this);
            mLifecycleRegistry.setCurrentState(Lifecycle.State.INITIALIZED);
            this.binder=bindeing;
            this.binder.setLifecycleOwner(mLifecycleOwner);

        }

        public void bind(ModelFall modelFall) {
            binder.setModel(modelFall);
            binder.executePendingBindings();
        }

        void markAttach() {
            mLifecycleRegistry.setCurrentState(Lifecycle.State.STARTED);
        }

        void markDetach() {
            mLifecycleRegistry.setCurrentState(Lifecycle.State.DESTROYED);
        }

        @NonNull
        @Override
        public Lifecycle getLifecycle() {
            return mLifecycleRegistry;
        }
    }

    public void updateAll(List<ModelFall> newData){
        this.date.addAll(newData);
        notifyDataSetChanged();
    }
}
