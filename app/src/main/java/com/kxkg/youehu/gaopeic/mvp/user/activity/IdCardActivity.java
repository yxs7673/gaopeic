package com.kxkg.youehu.gaopeic.mvp.user.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kxkg.youehu.developlib.base.BaseActivity;
import com.kxkg.youehu.developlib.dialog.MyDialogHint;
import com.kxkg.youehu.developlib.util.ActivityStack;
import com.kxkg.youehu.gaopeic.R;
import com.kxkg.youehu.gaopeic.cash.UserCash;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.NurseInfo;
import com.kxkg.youehu.gaopeic.entity.UploadEntity;
import com.kxkg.youehu.gaopeic.mvp.LoginActivity;
import com.kxkg.youehu.gaopeic.mvp.base.BaseView;
import com.kxkg.youehu.gaopeic.mvp.user.presenter.NurseInfoPresenter;
import com.kxkg.youehu.gaopeic.util.CommnetUtil;
import com.kxkg.youehu.gaopeic.util.GlideImageLoader;
import com.kxkg.youehu.gaopeic.util.OkHttpUpload;
import com.kxkg.youehu.gaopeic.util.WindowUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class IdCardActivity extends BaseActivity {


    @BindView(R.id.titlebar_info)
    ImageView titlebarInfo;
    @BindView(R.id.titlebar_title)
    TextView titlebarTitle;
    @BindView(R.id.titlebar_select)
    TextView titlebarSelect;
    @BindView(R.id.titlebar_more)
    TextView titlebarMore;
    @BindView(R.id.title_bar_ll)
    LinearLayout titleBarLl;
    @BindView(R.id.name_card_et)
    EditText nameCardEt;
    @BindView(R.id.sex_card_tv)
    TextView sexCardTv;
    @BindView(R.id.no_card_et)
    EditText noCardEt;
    @BindView(R.id.special_card_et)
    EditText specialCardEt;
    @BindView(R.id.card_up_btn)
    TextView cardUpBtn;
    @BindView(R.id.card_up_img)
    ImageView cardUpImg;
    @BindView(R.id.card_down_btn)
    TextView cardDownBtn;
    @BindView(R.id.card_down_img)
    ImageView cardDownImg;
    @BindView(R.id.card_confirm_btn)
    Button cardConfirmBtn;
    @BindView(R.id.licensed_card_tv)
    TextView licensedCardTv;
    @BindView(R.id.licensed_card_img)
    ImageView licensedCardImg;
    @BindView(R.id.health_card_tv)
    TextView healthCardTv;
    @BindView(R.id.health_card_img)
    ImageView healthCardImg;

    private final int IMG_IDCARDUP = 1004;
    private final int IMG_IDCARDDOWN = 1005;
    private final int IMG_HEALTH = 1006;
    private final int IMG_CERTIFICATE = 1007;
    private Intent intent;
    ArrayList<ImageItem> images = null;
    private ImagePicker imagePicker;
    private OkHttpUpload okHttpUpload;
    private String idCardUp;
    private String idCardDown;
    private String healthCertificate;
    private String licensed;
    private NurseInfoPresenter infoPresenter;
    private NurseInfo nurseInfo;
    @Override
    protected Context getActivityContext() {
        return this;
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_id_card);
    }

    @Override
    protected void findViewById() {
        titlebarTitle.setText("个人认证");
        titlebarInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void setListener() {
        cardUpBtn.setOnClickListener(this);
        cardDownBtn.setOnClickListener(this);
        licensedCardTv.setOnClickListener(this);
        healthCardTv.setOnClickListener(this);
        cardConfirmBtn.setOnClickListener(this);

    }

    @Override
    protected void processLogic() {
        nurseInfo= (NurseInfo) getIntent().getSerializableExtra("info");
        if (nurseInfo!=null){
            initIdView(nurseInfo);
        }
        infoPresenter=new NurseInfoPresenter(view,mActivity);
        initImagePicker();
        if (!StringUtils.isEmpty(nurseInfo.getAuditStatus())&&StringUtils.equals("1",nurseInfo.getAuditStatus())){//审核通过后，信息不可编辑
            nameCardEt.setEnabled(false);
            sexCardTv.setOnClickListener(null);
            sexCardTv.setClickable(false);
            noCardEt.setEnabled(false);

        }else {
            nameCardEt.setEnabled(true);
            sexCardTv.setOnClickListener(this);
            sexCardTv.setClickable(true);
            noCardEt.setEnabled(true);
        }

    }

    /**
     * 设置个人数据
     * @param nurseInfo
     */
    private void initIdView(NurseInfo nurseInfo) {
        if (!StringUtils.isEmpty(nurseInfo.getCarerName())){
            nameCardEt.setText(nurseInfo.getCarerName());
        }

        if (!StringUtils.isEmpty(nurseInfo.getGender())){
            switch (nurseInfo.getGender()){
                case "1":
                    sexCardTv.setText("男");
                    break;
                case "2":
                    sexCardTv.setText("女");
                    break;
            }
        }

        if (!StringUtils.isEmpty(nurseInfo.getIdcard())){
            noCardEt.setText(nurseInfo.getIdcard());
        }

        if (!StringUtils.isEmpty(nurseInfo.getSpecialty())){
            specialCardEt.setText(nurseInfo.getSpecialty());
        }

        if (!StringUtils.isEmpty(nurseInfo.getIdCardUp())) {//头像
            Glide.with(mActivity).load(Constant.URL + nurseInfo.getIdCardUp()).placeholder(R.mipmap.ic_pic_loding).into(cardUpImg);
        }
        if (!StringUtils.isEmpty(nurseInfo.getIdCardDown())) {//头像
            Glide.with(mActivity).load(Constant.URL + nurseInfo.getIdCardDown()).placeholder(R.mipmap.ic_pic_loding).into(cardDownImg);
        }
        if (!StringUtils.isEmpty(nurseInfo.getLicensed())) {//头像
            Glide.with(mActivity).load(Constant.URL + nurseInfo.getLicensed()).placeholder(R.mipmap.ic_pic_loding).into(licensedCardImg);
        }

        if (!StringUtils.isEmpty(nurseInfo.getHealthCertificate())) {//头像
            Glide.with(mActivity).load(Constant.URL + nurseInfo.getHealthCertificate()).placeholder(R.mipmap.ic_pic_loding).into(healthCardImg);
        }
    }

    private BaseView<NurseInfo> view=new BaseView<NurseInfo>() {
        @Override
        public void onSuccess(NurseInfo data) {
            finish();
        }

        @Override
        public void onFail(String msg) {
            ToastUtils.showShort(Constant.REQUEST_FAIL);
        }
    };


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
        imagePicker.setFocusWidth(WindowUtils.getWith(this) - 100);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(WindowUtils.getWith(this) - 200);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(WindowUtils.getWith(this));//保存文件的宽度。单位像素
        imagePicker.setOutPutY(WindowUtils.getWith(this) - 100);//保存文件的高度。单位像素

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.sex_card_tv:
                showSexChooseDialog();
                break;
            case R.id.card_up_btn:
                toPickImage(IMG_IDCARDUP);
                break;
            case R.id.card_down_btn:
                toPickImage(IMG_IDCARDDOWN);
                break;
            case R.id.licensed_card_tv:
                toPickImage(IMG_CERTIFICATE);
                break;
            case R.id.health_card_tv:
                toPickImage(IMG_HEALTH);
                break;
            case R.id.card_confirm_btn:
               confirm();
                break;
        }
    }



    private void confirm() {
        final NurseInfo nurseInfo=new NurseInfo();
        String name=nameCardEt.getText().toString().trim();
        String sex=sexCardTv.getText().toString().trim();
        String no=noCardEt.getText().toString().trim();
        String special=specialCardEt.getText().toString().trim();

        if (StringUtils.isEmpty(name)){
            ToastUtils.showShort("请输入姓名");
            return;
        }

        if (StringUtils.isEmpty(sex)){
            ToastUtils.showShort("请输入性别");
            return;
        }

        if (StringUtils.isEmpty(no)){
            ToastUtils.showShort("请输入身份证号");
            return;
        }

        CommnetUtil util = new CommnetUtil();



        if (no.contains("X")) {
            no.replaceAll("X", "x");
        }
        try {
            if (!"right".equals(util.IDCardValidate(no))) {
                ToastUtils.showShort("身份号码证输入有误");
                return;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        nurseInfo.setCarerName(name);
        if (StringUtils.equals("男",sex)){
            nurseInfo.setGender("1");
        }else {
            nurseInfo.setGender("2");
        }
        nurseInfo.setIdcard(no);
        if (!StringUtils.isEmpty(special)){
            nurseInfo.setSpecialty(special);
        }

        if (!StringUtils.isEmpty(idCardUp)){
        nurseInfo.setIdCardUp(idCardUp);
        }
        if (!StringUtils.isEmpty(idCardDown)){
        nurseInfo.setIdCardDown(idCardDown);
        }
        if (!StringUtils.isEmpty(licensed)){
        nurseInfo.setLicensed(licensed);
        }
        if (!StringUtils.isEmpty(healthCertificate)){
            nurseInfo.setHealthCertificate(healthCertificate);
        }

        nurseInfo.setId(UserCash.getUser().getId());


        new MyDialogHint(mActivity, R.style.MyDialogConfirm,
                "确定提交吗？", new MyDialogHint.ClickCallBack() {
            @Override
            public void onCancelClick() {

            }

            @Override
            public void onOkClick() {
                infoPresenter.loadData(nurseInfo);
            }
        }).show();

    }




    private void toPickImage(int request) {
        intent = new Intent(mActivity, ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_IMAGES, images);
        //ImagePicker.getInstance().setSelectedImages(images);
        startActivityForResult(intent, request);

    }


    /* 性别选择框 */
    private void showSexChooseDialog() {

        List<String> sexList = new ArrayList<String>();
        sexList.clear();
        sexList.add("男");
        sexList.add("女");

        ActivityStack.getLastActivity().showListSingleDialog(sexList, new ListDialogListener() {
            @Override
            public void itemClick(int position) {

            }

            @Override
            public void itemClick(String str) {
                sexCardTv.setText(str);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null) {
                switch (requestCode) {

                    case IMG_IDCARDUP://身份证正面
                        images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                        imagePicker.getImageLoader().displayImage(IdCardActivity.this, images.get(0).path, cardUpImg, 1000, 1000);
                        initHeadView(images.get(0).path, IMG_IDCARDUP);
                        break;

                    case IMG_IDCARDDOWN://反面
                        images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                        imagePicker.getImageLoader().displayImage(IdCardActivity.this, images.get(0).path, cardDownImg, 1000, 1000);
                        initHeadView(images.get(0).path, IMG_IDCARDDOWN);
                        break;

                    case IMG_CERTIFICATE://执业证
                        images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                        imagePicker.getImageLoader().displayImage(IdCardActivity.this, images.get(0).path, licensedCardImg, 1000, 1000);
                        initHeadView(images.get(0).path, IMG_CERTIFICATE);
                        break;

                    case IMG_HEALTH:
                        images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                        imagePicker.getImageLoader().displayImage(IdCardActivity.this, images.get(0).path, healthCardImg, 1000, 1000);
                        initHeadView(images.get(0).path, IMG_HEALTH);
                        break;

                }
            } else {
                ToastUtils.showShort("没有数据");
            }
        }

    }

    private void initHeadView(String path, int request) {

        switch (request) {

            case IMG_IDCARDUP:
                cardUpBtn.setClickable(false);
                break;
            case IMG_IDCARDDOWN://反面
                cardUpBtn.setClickable(false);
                break;

            case IMG_CERTIFICATE://执业证
                licensedCardTv.setClickable(false);
                break;

            case IMG_HEALTH:
                healthCardTv.setClickable(false);
                break;

        }

        getUploadImg(path, request);

    }



    private void getUploadImg(String path, final int request) {
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

                            case IMG_IDCARDUP:
                                idCardUp = uploadEntity.getData().get(0);
                                message.what = 3;
                                break;
                            case IMG_IDCARDDOWN://反面
                                idCardDown = uploadEntity.getData().get(0);
                                message.what = 5;
                                break;

                            case IMG_CERTIFICATE://执业证
                                licensed = uploadEntity.getData().get(0);
                                message.what = 7;
                                break;

                            case IMG_HEALTH:
                                healthCertificate = uploadEntity.getData().get(0);
                                message.what = 9;
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

                    case IMG_IDCARDUP:
                        message.what = 4;
                        break;
                    case IMG_IDCARDDOWN://反面
                        message.what = 6;
                        break;

                    case IMG_CERTIFICATE://执业证
                        message.what = 8;
                        break;

                    case IMG_HEALTH:
                        message.what = 10;
                        break;
                }
                handler.sendMessage(message);
            }

        });
        okHttpUpload.main(path, "personInfoImg");//type为该护工手机号

    }



    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case 3:
                    ToastUtils.showShort("身份证正面上传成功");
                    break;
                case 4:
                    ToastUtils.showShort("身份证正面上传失败，请重新上传");
                    break;
                case 5:
                    ToastUtils.showShort("身份证反面上传成功");
                    break;
                case 6:
                    ToastUtils.showShort("身份证反面上传失败，请重新上传");
                    break;

                case 7:
                    ToastUtils.showShort("执业证上传成功");
                    break;
                case 8:
                    ToastUtils.showShort("执业证上传失败，请重新上传");
                    break;
                case 9:
                    ToastUtils.showShort("健康证上传成功");
                    break;
                case 10:
                    ToastUtils.showShort("健康证上传失败，请重新上传");
                    break;

            }
        }
    };
}
