package hz.com.hzdrag.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import hz.com.hzdrag.R;
import hz.com.hzdrag.adapter.MyAdapter;
import hz.com.hzdrag.common.DefineView;
import hz.com.hzdrag.entity.CategoriesBean;
import hz.com.hzdrag.fragment.base.BaseFragment;
import hz.com.hzdrag.widget.DividerItemDecoration;

import static hz.com.hzdrag.R.id.refreshLayout;

/**
 * 当前类注释:页面Fragment
 * ProjectName：App36Kr_CNK
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class PageFragment extends BaseFragment implements DefineView {

    private View mView;
    private static final String KEY = "EXTRA";
    private CategoriesBean categoriesBean;

    private RecyclerView mRecyclerView;
    private MyAdapter mMyAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private RefreshLayout mRefreshLayout;

    public static PageFragment newInstance(CategoriesBean extra) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, extra);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            categoriesBean = (CategoriesBean) bundle.getSerializable(KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 初始化只加载一次数据
        if (mView == null) {
            mView = inflater.inflate(R.layout.page_fragment_layout, container, false);
            initView();
            initValidata();
            initListener();
            bindData();
            initRefreshLayout();
        }
        return mView;
    }

    @Override
    public void initView() {
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mMyAdapter = new MyAdapter(getActivity(), getData());
        mMyAdapter.setOnClickListener(new MyAdapter.OnClickListener() {
            @Override
            public void onClick(View v, int postion, ArrayList<String> data) {
                Toast.makeText(getActivity(), "点击：" + data.get(postion), Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.my_recycler_view);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        // 设置adapter
        mRecyclerView.setAdapter(mMyAdapter);
    }

    private void initRefreshLayout() {
        mRefreshLayout = (RefreshLayout) mView.findViewById(refreshLayout);
        mRefreshLayout.setEnableAutoLoadmore(true);  //开启自动加载功能（非必须）
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                refreshlayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mMyAdapter.updateData(getData());
                        refreshlayout.finishRefresh();
                    }
                }, 2000);
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                refreshlayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mMyAdapter.addData(getData());
                        refreshlayout.finishLoadmore();
                    }
                }, 2000);
            }
        });

        //mRefreshLayout.autoRefresh();
    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(i + " " + categoriesBean.getTitle());
        }
        return data;
    }

    @Override
    public void initValidata() {
    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {

    }
}
