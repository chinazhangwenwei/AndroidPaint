package com.xol.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xol.model.PaintViewModel;

/**
 * Created by wwzhang on 2019/3/11
 */
public class PaintContentFragment extends Fragment {


    private PaintViewModel mPaintViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null == getArguments()) {
            throw new IllegalArgumentException("初始化参数错误！");
        }
        mPaintViewModel = (PaintViewModel) getArguments().getParcelable("model");
    }

    public static PaintContentFragment createFragment(Bundle bundle) {

        PaintContentFragment fragment = new PaintContentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(mPaintViewModel.mLayoutId, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
