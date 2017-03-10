package com.prowareness.contact.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prowareness.contact.BaseActivity;
import com.prowareness.contact.R;
import com.prowareness.contact.adapters.ContactAdapter;
import com.prowareness.contact.bean.ContactRes;
import com.prowareness.contact.bean.Result;
import com.prowareness.contact.helper.ApiService;
import com.prowareness.contact.helper.AppConstant;
import com.prowareness.contact.helper.AppPreferenceManager;
import com.prowareness.contact.interfaces.Callbacks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Naveen Rawat on 10-03-2017.
 */

public class ContactActivity extends BaseActivity implements Callbacks {

    private ArrayList<Result> resultList = new ArrayList<>();
    private ContactAdapter mAdapter;
    private RecyclerView recyclerView;
    private Context context;
    private ViewAnimator animator;
    private Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        context = this;
        gson = new GsonBuilder().setPrettyPrinting().create();
        findViewByIds();
        init();
    }

    private void findViewByIds() {

        animator = (ViewAnimator) findViewById(R.id.viewAnimator);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ContactAdapter(this, resultList, this);
        recyclerView.setAdapter(mAdapter);

    }

    private void init() {

        if (AppPreferenceManager.getInstance(context).getString(AppConstant.PREF_LIST, null) == null) {
            fetchData();
        } else {
            resultList = AppConstant.getUpdatedList(context);
            if (resultList != null && resultList.size() > 0) {
                mAdapter = new ContactAdapter(this, resultList, this);
                recyclerView.setAdapter(mAdapter);
            } else {
                animator.setDisplayedChild(1);
            }
        }
    }


    private void fetchData() {

        showDialog();
        ApiService.getInstance().serviceRequest().getContacts().enqueue(new Callback<ContactRes>() {
            @Override
            public void onResponse(Call<ContactRes> call, Response<ContactRes> response) {
                dismissDialog();
                if (response.isSuccessful()) {
                    ContactRes res = response.body();
                    if (res.getStatus().equalsIgnoreCase("success")) {
                        ArrayList<Result> list = res.getResults();
                        if (list != null && list.size() > 0) {
                            resultList.addAll(list);
                            Collections.sort(resultList);
                            mAdapter.notifyDataSetChanged();
                            AppPreferenceManager.getInstance(context).saveString(AppConstant.PREF_LIST, gson.toJson(resultList));
                        } else {
                            animator.setDisplayedChild(1);
                        }
                    } else {
                        Toast.makeText(context, R.string.text_response_error, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ContactRes> call, Throwable t) {
                t.printStackTrace();
                dismissDialog();
                if (t instanceof IOException) {
                    Toast.makeText(context, R.string.text_internet_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onItemClick(int position) {
        resultList.remove(position);
        mAdapter.notifyDataSetChanged();
        AppPreferenceManager.getInstance(context).saveString(AppConstant.PREF_LIST, gson.toJson(resultList));
        if (resultList.size() == 0) {
            animator.setDisplayedChild(1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.refresh) {
            init();
        }
        return super.onOptionsItemSelected(item);
    }
}
