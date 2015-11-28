package com.krsk.actionobservertoy;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;

/**
 * Created by bl-lia on 11/27/15.
 */
public class ListFragment extends Fragment {

    @Bind(R.id.list_item) RecyclerView mItemList;

    @Nullable ListAdapter mListAdapter;

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        initView();
        resetList();
        return view;
    }

    private void initView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mItemList.setLayoutManager(linearLayoutManager);

        mListAdapter = new ListAdapter();
        mItemList.setAdapter(mListAdapter);

        mListAdapter.getItemConnectableObservable().subscribe(new Action1<Item>() {
            @Override
            public void call(Item item) {
                Log.d(ListFragment.class.getSimpleName(), String.format("Item: %s", item.name));
            }
        });
    }

    private void resetList() {
        if (mListAdapter == null) {
            return;
        }

        final List<Item> items = createItems();
        mListAdapter.reset(items);
    }

    private List<Item> createItems() {
        final List<Item> items = new ArrayList<>();
        items.add(new Item("AAAAA"));
        items.add(new Item("BBBBB"));
        items.add(new Item("CCCCC"));
        items.add(new Item("DDDDD"));

        return items;
    }
}
