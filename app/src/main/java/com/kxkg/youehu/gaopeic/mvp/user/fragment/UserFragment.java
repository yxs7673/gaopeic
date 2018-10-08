package com.kxkg.youehu.gaopeic.mvp.user.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kxkg.youehu.developlib.base.BaseFragment;
import com.kxkg.youehu.developlib.dialog.MyDialogHint;
import com.kxkg.youehu.developlib.util.ActivityStack;
import com.kxkg.youehu.gaopeic.R;
import com.kxkg.youehu.gaopeic.cash.UserCash;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.NurseInfo;
import com.kxkg.youehu.gaopeic.entity.UploadEntity;
import com.kxkg.youehu.gaopeic.mvp.LoginActivity;
import com.kxkg.youehu.gaopeic.mvp.base.BaseView;
import com.kxkg.youehu.gaopeic.mvp.dialog.ChangeHeadDialog;
import com.kxkg.youehu.gaopeic.mvp.order.presenter.GetInfoPresenter;
import com.kxkg.youehu.gaopeic.mvp.user.activity.CashGetActivity;
import com.kxkg.youehu.gaopeic.mvp.user.activity.IdCardActivity;
import com.kxkg.youehu.gaopeic.mvp.user.activity.PersonalActivity;
import com.kxkg.youehu.gaopeic.mvp.user.activity.SetActivity;
import com.kxkg.youehu.gaopeic.mvp.user.presenter.NurseInfoPresenter;
import com.kxkg.youehu.gaopeic.util.GlideImageLoader;
import com.kxkg.youehu.gaopeic.util.OkHttpUpload;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by yxs on 2018/8/27.
 */

public class UserFragment extends BaseFragment {


    @BindView(R.id.img_user_head)
    ImageView imgUserHead;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_phone)
    TextView tvUserPhone;
    @BindView(R.id.ll_user_info)
    LinearLayout llUserInfo;
    @BindView(R.id.tv_user_balance)
    TextView tvUserBalance;
    @BindView(R.id.user_rb)
    RatingBar userRb;
    @BindView(R.id.tv_card_state)
    TextView tvCardState;
    @BindView(R.id.ll_id_card)
    LinearLayout llIdCard;
    @BindView(R.id.ll_put_cash)
    LinearLayout llPutCash;
    @BindView(R.id.personal_ll)
    LinearLayout personalLl;
    @BindView(R.id.user_set_ll)
    LinearLayout userSetLl;
    @BindView(R.id.log_out_btn)
    Button logOutBtn;
    @BindView(R.id.user_swip)
    SwipeRefreshLayout userSwip;

    private Intent intent;
    private final int USER_INFO = 1001;
    private final int ID_CARD = 1002;
    private final int PUT_CASH = 1003;
    private GetInfoPresenter getInfoPresenter;
    private NurseInfo nurseInfo;

    private ChangeHeadDialog headDialog;
    ArrayList<ImageItem> images = null;
    private ImagePicker imagePicker;
    private final int CHNAGE_HEAD = 1002;
    private OkHttpUpload okHttpUpload;
    private String headImg;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, boolean b) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    protected void processLogic() {
        getInfoPresenter = new GetInfoPresenter(nurseInfoBaseView, getContext());
    }

    @Override
    protected void initListener() {
        llUserInfo.setOnClickListener(onClickListener);
        llIdCard.setOnClickListener(onClickListener);
        llPutCash.setOnClickListener(onClickListener);
        personalLl.setOnClickListener(onClickListener);
        userSetLl.setOnClickListener(onClickListener);
        logOutBtn.setOnClickListener(onClickListener);
        userSwip.setColorSchemeColors(Color.parseColor("#FF4081"), Color.parseColor("#303F9F"));
        userSwip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getInfoPresenter.loadData();
            }
        });
    }

    @Override
    protected void initData() {
        getInfoPresenter.loadData();
        headDialog = new ChangeHeadDialog(getContext(), dialogClick);
        initImagePicker();
    }


    private View.OnClickListener dialogClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.dialog_choice_photo:
                    headDialog.dismiss();
                    intent = new Intent(getContext(), ImageGridActivity.class);
                    intent.putExtra(ImageGridActivity.EXTRAS_IMAGES, images);
                    //ImagePicker.getInstance().setSelectedImages(images);
                    startActivityForResult(intent, CHNAGE_HEAD);
                    break;
            }

        }
    };

    private BaseView<NurseInfo> nurseInfoBaseView = new BaseView<NurseInfo>() {
        @Override
        public void onSuccess(NurseInfo data) {
            if (userSwip.isRefreshing()) {
                userSwip.setRefreshing(false);
            }

            if (data != null) {
                nurseInfo = data;
                initUserView(nurseInfo);
            }
        }

        @Override
        public void onFail(String msg) {
            if (userSwip.isRefreshing()) {
                userSwip.setRefreshing(false);
            }
            ToastUtils.showShort(Constant.REQUEST_FAIL);
        }
    };


    private void initUserView(NurseInfo nurseInfo) {
        if (!StringUtils.isEmpty(nurseInfo.getCarerName())) {
            tvUserName.setText(nurseInfo.getCarerName());
        }
        if (!StringUtils.isEmpty(nurseInfo.getMobile())) {
            tvUserPhone.setText(nurseInfo.getMobile());
        }

        if (!StringUtils.isEmpty(nurseInfo.getAuditStatus()) && StringUtils.equals("1", nurseInfo.getAuditStatus())) {
            tvCardState.setVisibility(View.VISIBLE);
        } else {
            tvCardState.setVisibility(View.GONE);
        }

        if (!StringUtils.isEmpty(nurseInfo.getAvatar())) {//头像
            Glide.with(getContext()).load(Constant.URL + nurseInfo.getAvatar()).error(R.mipmap.ic_user_head_default).placeholder(R.mipmap.ic_pic_loding).into(imgUserHead);
        }
    }


    private void initImagePicker() {
        imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader()); //设置图片加载器
        imagePicker.setMultiMode(false);//单选
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);

        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        //  imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(600);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(600);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(800);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(800);//保存文件的高度。单位像素

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            intent = new Intent();
            switch (view.getId()) {
                case R.id.ll_user_info:
                    headDialog.show();
                    break;
                case R.id.ll_id_card:
                    intent.setClass(getContext(), IdCardActivity.class);
                    intent.putExtra("info", nurseInfo);
                    startActivityForResult(intent, ID_CARD);
                    break;
                case R.id.ll_put_cash:
                    intent.setClass(getContext(), CashGetActivity.class);
                    intent.putExtra("info", nurseInfo);
                    startActivityForResult(intent, PUT_CASH);
                    break;
                case R.id.personal_ll://个体推荐
                    intent.setClass(getContext(), PersonalActivity.class);
                    intent.putExtra("info", nurseInfo);
                    startActivity(intent);
                    break;
                case R.id.user_set_ll:
                    intent.setClass(getContext(), SetActivity.class);
                    startActivity(intent);
                    break;
                case R.id.log_out_btn:
                    logout();
                    break;
            }
        }
    };

    private void logout() {
        new MyDialogHint(getContext(), R.style.MyDialogConfirm,
                "确定退出登录吗？", new MyDialogHint.ClickCallBack() {
            @Override
            public void onCancelClick() {

            }

            @Override
            public void onOkClick() {
                UserCash.cleanUser();
                ActivityStack.finishAll();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                getContext().startActivity(intent);
            }
        }).show();

    }

    @Override
    public void onResume() {
        super.onResume();
        getInfoPresenter.loadData();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null) {
                switch (requestCode) {
                    case CHNAGE_HEAD:
                        images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                        imagePicker.getImageLoader().displayImage(getActivity(), images.get(0).path, imgUserHead, 1000, 1000);
                        initHeadView(images.get(0).path, CHNAGE_HEAD);
                        break;
                }
            }
        }
    }

    private void initHeadView(String path, int request) {
        getUploadImg(path, request);
    }

    private void getUploadImg(String img, final int request) {
        okHttpUpload = new OkHttpUpload();
        okHttpUpload.setUploadListener(new OkHttpUpload.UploadListener() {
            @Override
            public void getImgPath(String image) {
                Gson gson = new Gson();
                UploadEntity uploadEntity = gson.fromJson(image, UploadEntity.class);
                if (uploadEntity != null) {
                    if (uploadEntity.getData() != null && uploadEntity.getData().size() > 0) {
                        Message message = handler.obtainMessage();
                        switch (request) {
                            case CHNAGE_HEAD:
                                headImg = uploadEntity.getData().get(0);
                                message.what = 1;
                                break;
                        }
                        handler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(String msg) {

                Message message = handler.obtainMessage();
                switch (request) {
                    case CHNAGE_HEAD:
                        message.what = 2;
                        break;

                }
                handler.sendMessage(message);
            }

        });
        okHttpUpload.main(img, "head");//type为该护工手机号

    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1://成功
                    ToastUtils.showShort("头像上传成功");
                    changeHeadImg();
                    break;
                case 2://失败
                    ToastUtils.showShort("头像上传失败，请重新上传");
                    break;
            }
        }
    };

    private void changeHeadImg() {
        NurseInfoPresenter infoPresenter = new NurseInfoPresenter(view, getContext());
        final NurseInfo nurseInfo = new NurseInfo();
        nurseInfo.setId(UserCash.getUser().getId());
        nurseInfo.setAvatar(headImg);
        infoPresenter.loadData(nurseInfo);

    }


    private BaseView<NurseInfo> view = new BaseView<NurseInfo>() {
        @Override
        public void onSuccess(NurseInfo data) {
            if (!StringUtils.isEmpty(headImg)) {//头像
                Glide.with(getContext()).load(Constant.URL + headImg).placeholder(R.mipmap.ic_pic_loding).into(imgUserHead);
            }
        }

        @Override
        public void onFail(String msg) {
            ToastUtils.showShort(Constant.REQUEST_FAIL);
        }
    };

}
