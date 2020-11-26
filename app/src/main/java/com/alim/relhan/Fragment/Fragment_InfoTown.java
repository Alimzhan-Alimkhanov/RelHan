package com.alim.relhan.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alim.relhan.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_InfoTown extends Fragment {


    private  String link;

    public Fragment_InfoTown(String link) {
        this.link = link;
    }


    WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__info_town, container, false);

        Log.d("Testlog",link);

        webView = view.findViewById(R.id.wbview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(link);


        return view;
    }

}
