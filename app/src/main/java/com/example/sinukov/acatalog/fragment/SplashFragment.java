package com.example.sinukov.acatalog.fragment;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sinukov.acatalog.R;

import java.util.concurrent.TimeUnit;

public class SplashFragment extends Fragment {

    public SplashFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        SplashTask splashTask = new SplashTask();
        splashTask.execute();

        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    class SplashTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (getActivity() != null) {
                getActivity().getFragmentManager().popBackStack();
            }

            return null;
        }
    }
}