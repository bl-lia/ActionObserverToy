package com.krsk.actionobservertoy;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.observables.ConnectableObservable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * Created by bl-lia on 11/27/15.
 */
public class ListAdapter extends RecyclerView.Adapter {

    private final List<Item> mItems = new ArrayList<>();
    private Observable<Item> mItemConnectableObservable;

    public ListAdapter() {
        mItemConnectableObservable = ConnectableObservable.<Item>empty();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final View view = LayoutInflater.from(context).inflate(R.layout.list_row_item, parent, false);
        return ItemViewHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            final Item item = mItems.get(position);
            ((ItemViewHolder) holder).applyItem(item);
            if (mItemConnectableObservable == null) {
                mItemConnectableObservable = ((ItemViewHolder) holder).getItemConnectableObservable();
            } else {
                mItemConnectableObservable.mergeWith(((ItemViewHolder) holder).getItemConnectableObservable());
            }
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public Observable<Item> getItemConnectableObservable() {
        return mItemConnectableObservable;
    }

    public void reset(List<Item> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text_item)
        TextView mItemText;

        @Nullable
        private Item mItem;

        private final ConnectableObservable<Item> mItemConnectableObservable;

        public static ItemViewHolder newInstance(View parent) {
            return new ItemViewHolder(parent);
        }

        public ItemViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            final Observable<Item> observable = Observable.create(new Observable.OnSubscribe<Item>() {
                @Override
                public void call(final Subscriber<? super Item> subscriber) {
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (mItem != null) {
                                subscriber.onNext(mItem);
                            }

                        }
                    });
                }
            });
            mItemConnectableObservable = observable.publish();

        }

        public ConnectableObservable<Item> getItemConnectableObservable() {
            return mItemConnectableObservable;
        }

        public void applyItem(Item item) {
            mItem = item;
            mItemText.setText(item.name);
        }
    }
}
