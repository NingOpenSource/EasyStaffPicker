package org.ning.EasyStaffPicker;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.ning.EasyStaffPicker.utils.ViewUtils;

import java.math.BigDecimal;

/**
 * 滚动的标尺控件
 *
 * @author yanning<br>
 *         2016年3月21日上午1:40:36<br>
 */
public class EasyScrollStaffPicker extends LinearLayout {
	private EasyStaffPicker easyStaffPicker;
	private int staff_guide_color = Color.RED;
	private Paint paint_guide;
	private static final int guide_size = 40;
	// private Paint paint_blank = null;
	private MHorizontalScrollView horizontalScrollView;
	private MScrollView scrollView;
	private LinearLayout root;

	@TargetApi(21)
	public EasyScrollStaffPicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
		initViews(attrs);
	}

	@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
	public EasyScrollStaffPicker(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initViews(attrs);
	}

	public EasyScrollStaffPicker(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initViews(attrs);
	}

	public EasyScrollStaffPicker(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initViews(null);
	}

	private void initViews(AttributeSet attrs) {
		if (attrs != null) {
			easyStaffPicker = new EasyStaffPicker(getContext(), attrs);
			TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.EasyStaffPicker);
			if (typedArray != null) {
				setStaff_guide_color(
						typedArray.getColor(R.styleable.EasyStaffPicker_staff_guide_color, Color.RED));
			}
		} else {
			easyStaffPicker = new EasyStaffPicker(getContext());
		}
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		if (easyStaffPicker.isIs_herizontal()) {
			horizontalScrollView = new MHorizontalScrollView(getContext());
			layoutParams.topMargin = guide_size;
			horizontalScrollView.setLayoutParams(layoutParams);
			LayoutParams layoutParams2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
			root = new LinearLayout(getContext());
			root.addView(easyStaffPicker, layoutParams2);
			horizontalScrollView.addView(root, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			addView(horizontalScrollView);
			horizontalScrollView.setHorizontalScrollBarEnabled(false);
		} else {
			scrollView = new MScrollView(getContext());
			layoutParams.leftMargin = guide_size;
			scrollView.setLayoutParams(layoutParams);
			LayoutParams layoutParams2 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			root = new LinearLayout(getContext());
			root.addView(easyStaffPicker, layoutParams2);
			scrollView.addView(root, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			addView(scrollView);
			scrollView.setVerticalScrollBarEnabled(false);
		}

		isFirstSet = true;
		paint_guide = new Paint();
		paint_guide.setColor(staff_guide_color);
		paint_guide.setStrokeWidth(2);
		paint_guide.setAntiAlias(true);
		// paint_blank.setAlpha(10);
		setBackgroundColor(Color.TRANSPARENT);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@SuppressWarnings("unused")
	private int caculateStaffSize() {
		int i1 = Math.abs(easyStaffPicker.getStaff_start() - easyStaffPicker.getStaff_end())
				* easyStaffPicker.getStaff_unit_value() + easyStaffPicker.getStaff_unit_tag_textSize();

		return 0;
	}

	@SuppressWarnings("unused")
	private boolean isFirstSet = false;

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		/**
		 * 繪製中間的三角形
		 */
		int square_bottom_side = 30;
		int square_height = guide_size;
		/**
		 * 调整位置
		 */

		/**
		 * 處理半個字符的位置差
		 */
		if (easyStaffPicker.isIs_herizontal()) {
			int i0 = getMeasuredWidth();
			int i1 = i0 / 2;
			int i2 = square_bottom_side / 2;
			int c = i1 - easyStaffPicker.getStaff_unit_tag_textSize() / 2;
			Path path = new Path();
			path.moveTo(i1 - i2, 0);// 此点为多边形的起点
			path.lineTo(i1, square_height);
			path.lineTo(i1 + i2, 0);
			path.close(); // 使这些点构成封闭的多边形
			canvas.drawPath(path, paint_guide);
			root.setPadding(c, 0, c, 0);
		} else {
			int i0 = getMeasuredHeight();
			int i1 = i0 / 2;
			int i2 = square_bottom_side / 2;
			int c = i1 - easyStaffPicker.getStaff_unit_tag_textSize() / 2;
			Path path = new Path();
			path.moveTo(0, i1 - i2);// 此点为多边形的起点
			path.lineTo(square_height, i1);
			path.lineTo(0, i1 + i2);
			path.close(); // 使这些点构成封闭的多边形
			canvas.drawPath(path, paint_guide);
			root.setPadding(0, c, 0, c);

		}
		// LinearGradient linearGradientTop = new LinearGradient(0, 0,
		// getMeasuredWidth(), getMeasuredHeight(),
		// Color.argb(255, 255, 255, 255), Color.argb(0, 255, 255, 255),
		// Shader.TileMode.MIRROR);
		// LinearGradient linearGradientBottom = new
		// LinearGradient(getMeasuredWidth(), getMeasuredHeight(), 0, 0,
		// Color.argb(255, 255, 255, 255), Color.argb(0, 255, 255, 255),
		// Shader.TileMode.MIRROR);
		// Paint paint = new Paint();
		// paint.setColor(Color.GREEN);
		//
		// if (kzStaffPickerView.isIs_herizontal()) {
		// // canvas.dra
		// paint_blank.setShader(linearGradientTop);
		// canvas.drawRect(0, 0, getMeasuredWidth() / 4, getMeasuredHeight(),
		// paint_blank);
		// paint_blank.setShader(linearGradientBottom);
		// canvas.drawRect(getMeasuredWidth(), getMeasuredHeight(),
		// getMeasuredWidth() / 4 * 3, 0, paint_blank);
		// } else {
		//
		// paint_blank.setShader(linearGradientTop);
		// canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight() / 4,
		// paint_blank);
		//
		// paint_blank.setShader(linearGradientBottom);
		// canvas.drawRect(getMeasuredWidth(), getMeasuredHeight(), 0,
		// getMeasuredHeight() / 4 * 3, paint_blank);
		// }
	}

	public int getStaff_guide_color() {
		return staff_guide_color;
	}

	// /**
	// * 繪製遮罩層
	// *
	// * @author yanning<br>
	// * 2016年3月21日上午9:24:45<br>
	// * @param canvas
	// */
	// private void drawShade(Canvas canvas) {
	// // TODO Auto-generated method stub
	//
	// }

	public void setStaff_guide_color(int staff_guide_color) {
		this.staff_guide_color = staff_guide_color;
	}

	public OnStaffValueChangeListener getOnStaffValueChangeListener() {
		return onStaffValueChangeListener;
	}

	/**
	 * 设置标尺值的监听器
	 * @param onStaffValueChangeListener
     */
	public void setOnStaffValueChangeListener(OnStaffValueChangeListener onStaffValueChangeListener) {
		this.onStaffValueChangeListener = onStaffValueChangeListener;
	}

	private OnStaffValueChangeListener onStaffValueChangeListener = new OnStaffValueChangeListener() {

		@Override
		public void onChange(int unit_index) {
			// TODO Auto-generated method stub
			Log.i("Lyon", "刻度值：" + (easyStaffPicker.getStaff_start() + unit_index)
					+ easyStaffPicker.getStaff_unit_name());
		}

        @Override
        public void onChangeValue(String unit_tag_name, double unit_tag_value) {

            Log.i("Lyon", "刻度值：" + unit_tag_name);
            Log.i("Lyon", "刻度值：" + unit_tag_value);
        }

	};

	private interface Scroll {
	}

	/**
	 * 监听数值变化
	 *
	 * @author yanning<br>
	 *         2016年3月21日上午10:56:15<br>
	 */
	public static interface OnStaffValueChangeListener {
		/**
		 *
         * @param unit_index    单位值
         */
		public void onChange(int unit_index);

        /**
         * @param unit_tag_name 代表的实际值（一般为物理值，包含单位名称）
         * @param unit_tag_value          代表实际值（一般为物理值，不包含单位名称）
         */
		public void onChangeValue(String unit_tag_name,double unit_tag_value);
	}

	private class MScrollView extends ScrollView implements Scroll {

		@TargetApi(21)
		public MScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
			super(context, attrs, defStyleAttr, defStyleRes);
			// TODO Auto-generated constructor stub
		}

		public MScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
			super(context, attrs, defStyleAttr);
			// TODO Auto-generated constructor stub
		}

		public MScrollView(Context context, AttributeSet attrs) {
			super(context, attrs);
			// TODO Auto-generated constructor stub
		}

		public MScrollView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void onScrollChanged(int l, int t, int oldl, int oldt) {
			// TODO Auto-generated method stub
			super.onScrollChanged(l, t, oldl, oldt);
			float d = t - (ViewUtils.getMinimumHeight(this) / 2f) - easyStaffPicker.getStaff_unit_tag_textSize() / 2f;
			// int i = (int) Math.ceil(d /
			// Float.valueOf(kzStaffPickerView.getStaff_unit_value())) + 1;
			int i = new BigDecimal(d / Float.valueOf(easyStaffPicker.getStaff_unit_value()) + "")
					.setScale(0, BigDecimal.ROUND_HALF_UP).intValue() + 1;
			if (onStaffValueChangeListener != null) {
				onStaffValueChangeListener.onChange(i);
				if (easyStaffPicker.getGetUnitTagName() != null) {
					int value = Integer.valueOf(easyStaffPicker.getGetUnitTagName().get(
							easyStaffPicker.getStaff_start(), easyStaffPicker.getStaff_end(), i,
							easyStaffPicker.getStaff_end() - easyStaffPicker.getStaff_start() + 1));
					onStaffValueChangeListener.onChangeValue(value + easyStaffPicker.getStaff_unit_name(),value);
				}
			}
		}
	}

	private class MHorizontalScrollView extends HorizontalScrollView implements Scroll {
		@TargetApi(21)
		public MHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
			super(context, attrs, defStyleAttr, defStyleRes);
			// TODO Auto-generated constructor stub
		}

		public MHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
			super(context, attrs, defStyleAttr);
			// TODO Auto-generated constructor stub
		}

		public MHorizontalScrollView(Context context, AttributeSet attrs) {
			super(context, attrs);
			// TODO Auto-generated constructor stub
		}

		public MHorizontalScrollView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void onScrollChanged(int l, int t, int oldl, int oldt) {
			// TODO Auto-generated method stub
			super.onScrollChanged(l, t, oldl, oldt);
			float d = l - (ViewUtils.getMinimumWidth(this) / 2f) - easyStaffPicker.getStaff_unit_tag_textSize() / 2f;
			// int i = (int) Math.ceil(d /
			// Float.valueOf(kzStaffPickerView.getStaff_unit_value())) + 1;

			int i = new BigDecimal(d / Float.valueOf(easyStaffPicker.getStaff_unit_value()) + "")
					.setScale(0, BigDecimal.ROUND_HALF_UP).intValue() + 1;
			if (onStaffValueChangeListener != null) {
				onStaffValueChangeListener.onChange(
						easyStaffPicker.isReverseOrder() ? easyStaffPicker.getStaffLengthWithUnit() - i : i);
				if (easyStaffPicker.getGetUnitTagName() != null) {
					int value = Integer.valueOf(easyStaffPicker.getGetUnitTagName().get(
							easyStaffPicker.getStaff_start(), easyStaffPicker.getStaff_end(), i,
							easyStaffPicker.getStaff_end() - easyStaffPicker.getStaff_start() + 1));
					onStaffValueChangeListener.onChangeValue(value + easyStaffPicker.getStaff_unit_name(),value);
				}
			}
		}

	}

	/**
	 * 可重载用于自定义标签
	 *
	 * @author yanning<br>
	 *         2016年3月21日上午11:42:32<br>
	 * @param getUnitTagName
	 */
	public void setGetUnitTagName(EasyStaffPicker.GetUnitTagName getUnitTagName) {
		// TODO Auto-generated method stub
		easyStaffPicker.setGetUnitTagName(getUnitTagName);
	}

	/**
	 * 空白层
	 *
	 * @author yanning<br>
	 *         2016年3月21日上午9:02:47<br>
	 */
	@SuppressWarnings("unused")
	private class BlankView extends LinearLayout {

		public BlankView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			// TODO Auto-generated method stub
			return false;
		}

	}

	/**
	 * 主动滚动到
	 *
	 * @author yanning<br>
	 *         2016年3月21日上午11:31:11<br>
	 * @param unit_index (绝地值)
	 */
	private void scroll2ByUnitIndex(int unit_index) {
		final int scroll2 = Math.round((unit_index) * easyStaffPicker.getStaff_unit_value()
				+ easyStaffPicker.getStaff_unit_tag_textSize() / 2f);
		if (easyStaffPicker.isIs_herizontal()) {
			horizontalScrollView.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					horizontalScrollView.scrollTo(scroll2, 0);
				}
			});
		} else {
			scrollView.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					scrollView.scrollTo(0, scroll2);
				}
			});

		}
	}

	/**
	 * 主动滚动到
	 *
	 * @author yanning<br>
	 *         2016年3月21日上午11:31:11<br>
	 * @param value (物理值)
	 */
	public void scroll2ByUnitValue(int value) {
		int unitValue = easyStaffPicker.isReverseOrder() ? easyStaffPicker.getStaff_end() - value - 1
				: value - easyStaffPicker.getStaff_start() - 1;
		scroll2ByUnitIndex(unitValue);
	}
}
