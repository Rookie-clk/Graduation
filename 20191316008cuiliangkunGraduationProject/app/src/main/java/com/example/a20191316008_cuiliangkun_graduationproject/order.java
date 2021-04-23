package com.example.a20191316008_cuiliangkun_graduationproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20191316008_cuiliangkun_graduationproject.bean.Hotel;
import com.example.a20191316008_cuiliangkun_graduationproject.bean.Order;
import com.example.a20191316008_cuiliangkun_graduationproject.database.HotelDBHelper;
import com.example.a20191316008_cuiliangkun_graduationproject.database.OrderDBHelper;
import com.example.a20191316008_cuiliangkun_graduationproject.database.UserDBHelper;

import java.util.List;

public class order extends Fragment {
    private ListView order;
    private ImageView img;
    public View view;
    private List<Order> Allorder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_order,container,false);

        init();
        getData();

        return view;
    }

    private void getData() {

        OrderDBHelper orderDBHelper = new OrderDBHelper(getActivity(),"orderinfo",null,1);
        if (orderDBHelper.findOrder()){
            Allorder = orderDBHelper.findAllOrder();
            OrderAdapter orderAdapter = new OrderAdapter();
            orderAdapter.notifyDataSetChanged();
            order.setAdapter(orderAdapter);

        }else{
            img.setVisibility(View.VISIBLE);
        }

    }
    public static Bitmap getPicFromBytes(byte[] bytes) {
        if (bytes != null)
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        return newBmp;
    }
    private void init() {
        order = view.findViewById(R.id.order_listview);
        img = view.findViewById(R.id.order_img);
        OrderDBHelper orderDBHelper = new OrderDBHelper(getActivity(),"orderinfo",null,1);

    }

    class OrderAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return Allorder.size();
        }

        @Override
        public Object getItem(int position) {
            return Allorder.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(getActivity(),R.layout.order_item,null);
            UserDBHelper userDBHelper = new UserDBHelper(getActivity(),"userinfo",null,1);
            HotelDBHelper hotelDBHelper = new HotelDBHelper(getActivity(),"hotelinfo",null,1);

            TextView hotelname  = convertView.findViewById(R.id.orderitem_hotelname);
            TextView hotelprice = convertView.findViewById(R.id.orderitem_hotelprice);
            TextView shenfenzheng = convertView.findViewById(R.id.orderitem_shenfenzheng);
            TextView phonenumber = convertView.findViewById(R.id.orderitem_phonenumber);
            TextView truename = convertView.findViewById(R.id.orderitem_truename);
            TextView leixing = convertView.findViewById(R.id.orderitem_leixing);
            ImageView hotelimg = convertView.findViewById(R.id.orderitem_img);

            final Order tmpOrder = Allorder.get(position);
            String tmphid = tmpOrder.getHid().toString();                   //根据order中的id查询民宿
            Hotel tmpHotel = hotelDBHelper.findHotelByid(tmphid);

            byte[] ImageByte = tmpHotel.getPicture();
            Bitmap ImageBitmap = getPicFromBytes(ImageByte);            //设置图片
            ImageBitmap = zoomBitmap(ImageBitmap,1000,800);
            hotelimg.setImageBitmap(ImageBitmap);
            hotelname.setText(tmpHotel.getName());
            hotelprice.setText("￥"+tmpOrder.getPrice());
            shenfenzheng.setText(tmpOrder.getShenfenzheng());
            phonenumber.setText(tmpOrder.getPhonenumber());
            truename.setText(tmpOrder.getTruename());
            leixing.setText(tmpHotel.getType());


            final ViewHolder holder = new ViewHolder();

            holder.delete = convertView.findViewById(R.id.orderitem_delete);
            holder.review = convertView.findViewById(R.id.orderitem_review);
            holder.detail = convertView.findViewById(R.id.orderitem_re);
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final commondialog commondialog = new commondialog(getActivity());
                    commondialog.setTitle("提示")
                            .setMessage("您确定要删除订单吗？")
                            .setPositive("确定")
                            .setNegative("取消")
                            .setOnClickButtonListener(new commondialog.OnclickButtonListener() {
                                @Override
                                public void onPositiveClick() {
                                    OrderDBHelper orderDBHelper = new OrderDBHelper(getActivity(),"orderinfo",null,1);
                                    if(!orderDBHelper.ExistOrderById(tmpOrder.getOid().toString())){
                                        Toast.makeText(getActivity(),"删除订单失败！",Toast.LENGTH_LONG);
                                    }else{
                                        orderDBHelper.deleteOrder(tmpOrder.getOid().toString());
                                        Toast.makeText(getActivity(),"删除订单成功！",Toast.LENGTH_LONG);
//
                                        if(!orderDBHelper.findOrder()){
                                            img.setVisibility(View.VISIBLE);
                                        }else{


                                        }
                                        commondialog.dismiss();
                                    }



                                }

                                @Override
                                public void onNegativeClick() {
                                    commondialog.dismiss();
                                }
                            });
                    commondialog.show();
                }
            });
            holder.review.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            holder.detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            return convertView;
        }
        class ViewHolder {
            Button delete;
            Button review;
            Button detail;
        }
    }
}
