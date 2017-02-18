package org.ning.EasyStaffPicker;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import java.math.BigDecimal;

/**
 * 標尺選擇控件
 * 
 * @author yanning<br>
 *         2016年3月17日下午2:05:16<br>
 */
public class EasyStaffPicker extends View {
	/**
	 * 是否为相反的顺序
	 */
	private boolean isReverseOrder = false;
	/**
	 * 默認的標尺方向
	 */
	private boolean is_herizontal = false;
	/**
	 * 标尺控件的刻度单位长度
	 */
	private int staff_unit_value = 10;
	/**
	 * 标尺的单位名称
	 */
	private String staff_unit_name = "cm";
	/**
	 * 刻度的起始位置
	 */
	private int staff_start = 20;
	/**
	 * 刻度的结束位置
	 */
	private int staff_end = 220;
	/**
	 * 刻度线主轴的宽度
	 */
	private int staff_line_width = 1;
	/**
	 * 刻度线条的宽度
	 */
	private int staff_unit_line_width = 1;
	/**
	 * 标记类型的线条的宽度
	 */
	private int staff_unit_tag_line_width = 1;
	/**
	 * 标记的间隔
	 */
	private int staff_unit_tag_space_num = 10;
	/**
	 * 标记的次级间隔
	 */
	private int staff_unit_sencodary_tag_space_num = 5;
	/**
	 * 标记次级类型的线条的宽度
	 */
	private int staff_unit_sencodary_tag_line_width = 1;
	/**
	 * 刻度線條的長度
	 */
	private int staff_unit_line_size = 10;
	/**
	 * 標記線條的刻度的長度
	 */
	private int staff_unit_tag_line_size = 30;
	/**
	 * 次级標記線條的刻度的長度
	 */
	private int staff_secondary_unit_tag_line_size = 20;
	/**
	 * 标尺的颜色
	 */
	private int staff_unit_line_color = Color.BLUE;
	/**
	 * 标签标尺的标题颜色
	 */
	private int staff_unit_tag_textColor = Color.BLUE;
	/**
	 * 标签标尺的标题大小
	 */
	private int staff_unit_tag_textSize = 30;
	/**
	 * 文字画笔
	 */
	private Paint paint_text = null;
	/**
	 * 标尺画笔
	 */
	private Paint paint_staff = null;
	/**
	 * 普通刻度画笔
	 */
	private Paint paint_unit = null;
	/**
	 * 刻度标签画笔
	 */
	private Paint paint_tag_unit = null;
	/**
	 * 次级刻度标签画笔
	 */
	private Paint paint_sencodary_tag_unit = null;
	/**
	 * 空白画笔
	 */
	private Paint paint_blank = null;

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public EasyStaffPicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
		init(attrs);
	}

	public EasyStaffPicker(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init(attrs);
	}

	public EasyStaffPicker(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(attrs);
	}

	public EasyStaffPicker(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(null);
	}

	private void init(AttributeSet attrs) {
		// TODO Auto-generated method stub
		if (attrs != null) {
			TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.EasyStaffPicker);
			if (typedArray == null)
				return;
			setIs_herizontal(typedArray.getBoolean(R.styleable.EasyStaffPicker_isHorizontal, false));
			setStaff_unit_value(typedArray.getDimensionPixelSize(R.styleable.EasyStaffPicker_staff_unit_value,
					staff_unit_value + 0));
			setStaff_unit_name(typedArray.getString(R.styleable.EasyStaffPicker_staff_unit_name));
			setStaff_start(typedArray.getInteger(R.styleable.EasyStaffPicker_staff_start, staff_start + 0));
			setStaff_end(typedArray.getInteger(R.styleable.EasyStaffPicker_staff_end, staff_end + 0));
			setStaff_line_width(typedArray.getDimensionPixelSize(R.styleable.EasyStaffPicker_staff_line_width,
					staff_line_width + 0));
			setStaff_unit_line_width(typedArray.getDimensionPixelSize(
					R.styleable.EasyStaffPicker_staff_unit_line_width, staff_unit_line_width + 0));
			setStaff_unit_tag_line_width(typedArray.getDimensionPixelSize(
					R.styleable.EasyStaffPicker_staff_unit_tag_line_width, staff_unit_tag_line_width + 0));

			setStaff_unit_tag_space_num(typedArray.getInteger(
					R.styleable.EasyStaffPicker_staff_unit_tag_space_num, staff_unit_tag_space_num + 0));
			setStaff_unit_sencodary_tag_space_num(
					typedArray.getInteger(R.styleable.EasyStaffPicker_staff_unit_sencodary_tag_space_num,
							staff_unit_sencodary_tag_space_num + 0));
			setStaff_unit_sencodary_tag_line_width(typedArray.getDimensionPixelSize(
					R.styleable.EasyStaffPicker_staff_unit_sencodary_tag_line_width,
					staff_unit_sencodary_tag_line_width + 0));

			setStaff_unit_line_size(typedArray.getDimensionPixelSize(
					R.styleable.EasyStaffPicker_staff_unit_line_size, staff_unit_line_size + 0));
			setStaff_unit_tag_line_size(typedArray.getDimensionPixelSize(
					R.styleable.EasyStaffPicker_staff_unit_tag_line_size, staff_unit_tag_line_size + 0));
			setStaff_secondary_unit_tag_line_size(typedArray.getDimensionPixelSize(
					R.styleable.EasyStaffPicker_staff_secondary_unit_tag_line_size,
					staff_secondary_unit_tag_line_size + 0));
			setStaff_unit_line_color(
					typedArray.getColor(R.styleable.EasyStaffPicker_staff_unit_line_color, Color.BLUE));
			setStaff_unit_tag_textColor(
					typedArray.getColor(R.styleable.EasyStaffPicker_staff_unit_tag_textColor, Color.BLUE));
			setStaff_unit_tag_textSize(typedArray.getDimensionPixelSize(
					R.styleable.EasyStaffPicker_staff_unit_tag_textSize, staff_unit_tag_textSize + 0));
			setReverseOrder(typedArray.getBoolean(R.styleable.EasyStaffPicker_isReverseOrder, isReverseOrder));
		}
		paint_staff = new Paint();
		paint_text = new Paint();
		paint_blank = new Paint();
		paint_sencodary_tag_unit = new Paint();
		paint_tag_unit = new Paint();
		paint_unit = new Paint();
		paint_staff.setColor(staff_unit_line_color);
		paint_staff.setStrokeWidth(staff_line_width);
		paint_staff.setAntiAlias(true);
		paint_text.setColor(staff_unit_tag_textColor);
		paint_text.setStrokeWidth(staff_line_width);
		paint_text.setTextSize(staff_unit_tag_textSize);
		paint_text.setAntiAlias(true);
		// paint_blank.setAlpha(0);
		paint_blank.setColor(Color.WHITE);
		paint_blank.setStrokeWidth(staff_line_width);
		paint_blank.setAntiAlias(true);
		// setBackgroundColor(Color.GREEN);

		paint_sencodary_tag_unit.setColor(staff_unit_line_color);
		paint_sencodary_tag_unit.setStrokeWidth(staff_unit_sencodary_tag_line_width);
		paint_sencodary_tag_unit.setAntiAlias(true);
		paint_tag_unit.setColor(staff_unit_line_color);
		paint_tag_unit.setStrokeWidth(staff_unit_tag_line_width);
		paint_tag_unit.setAntiAlias(true);
		paint_unit.setColor(staff_unit_line_color);
		paint_unit.setStrokeWidth(staff_unit_line_width);
		paint_unit.setAntiAlias(true);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		/**
		 * 
		 */
		// int st
		// for(){
		//
		// }
		drawMainLine(canvas);
		drawUnitLine(canvas);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		if (is_herizontal) {

			setMeasuredDimension(
					MeasureSpec.makeMeasureSpec(getContentLength() + 3 * 2 * getOTranslate(), MeasureSpec.EXACTLY),
					MeasureSpec.makeMeasureSpec(300, MeasureSpec.EXACTLY));
		} else {

			setMeasuredDimension(MeasureSpec.makeMeasureSpec(500, MeasureSpec.EXACTLY),
					MeasureSpec.makeMeasureSpec(getContentLength(), MeasureSpec.EXACTLY));
		}
		// super.onMeasure(widthMeasureSpec, getContentHeight());
	}

	public int getContentLength() {
		// TODO Auto-generated method stub
		return Math.abs(staff_end - staff_start) * staff_unit_value + staff_unit_tag_textSize;
	}

	public int getOTranslate() {
		return new BigDecimal("" + staff_unit_tag_textSize / 2f).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
	}

	/**
	 * 绘制主轴线
	 * 
	 * @author yanning<br>
	 *         2016年3月20日下午10:28:39<br>
	 */
	private void drawMainLine(Canvas canvas) {
		/**
		 * 原点偏移
		 */
		int oTranslate = getOTranslate();
		canvas.translate(oTranslate, oTranslate);
		int length = (staff_end - staff_start) * staff_unit_value;
		if (isIs_herizontal()) {
			canvas.drawLine(0, 0 + paint_staff.getStrokeWidth(), length, 0 + paint_staff.getStrokeWidth(), paint_staff);
		} else {
			canvas.drawLine(0 + paint_staff.getStrokeWidth(), 0, 0 + paint_staff.getStrokeWidth(), length, paint_staff);
			// canvas.drawLine(startX, startY, stopX, stopY, paint);
		}
	}

	/**
	 * 绘制刻度文本标题
	 * 
	 * @author yanning<br>
	 *         2016年3月20日下午10:30:39<br>
	 */
	private void drawTagText(Canvas canvas, String text, float x, float y) {
		if (staff_unit_name == null) {
			staff_unit_name = "";
		}
		canvas.drawText(text + staff_unit_name, x, y, paint_text);
	}

	/**
	 * 用于配置刻度标签名的函数（可重载）
	 * 
	 * @author yanning<br>
	 *         2016年3月21日上午12:40:52<br>
	 * @param index
	 * @return
	 */
	private String getUnitTag(int start, int end, int index, int count) {
		if (getUnitTagName != null) {
			return getUnitTagName.get(start, end, index, count);
		}
		return "";
	}

	/**
	 * 绘制刻度线
	 * 
	 * @author yanning<br>
	 *         2016年3月20日下午10:29:20<br>
	 */
	private void drawUnitLine(Canvas canvas) {
		int unit_all_num = staff_end - staff_start + 1;
		int oTranslate = getOTranslate();
		int unitTagMarginStart = (int) (0 + paint_staff.getStrokeWidth() + staff_unit_tag_line_size + oTranslate);
		for (int i = 0; i < unit_all_num; i++) {
			int x = staff_unit_value * i;
			int t = i;
			if (staff_unit_tag_space_num >= 0 && t % staff_unit_tag_space_num == 0) {
				if (isIs_herizontal()) {
					canvas.drawLine(x, 0 + paint_staff.getStrokeWidth(), x,
							0 + paint_staff.getStrokeWidth() + staff_unit_tag_line_size, paint_tag_unit);
					drawTagText(canvas, getUnitTag(staff_start, staff_end, t, unit_all_num), x - oTranslate,
							unitTagMarginStart + oTranslate);
				} else {
					canvas.drawLine(0 + paint_staff.getStrokeWidth(), x,
							0 + paint_staff.getStrokeWidth() + staff_unit_tag_line_size, x, paint_tag_unit);
					drawTagText(canvas, getUnitTag(staff_start, staff_end, t, unit_all_num), unitTagMarginStart,
							x + oTranslate);
					// canvas.drawLine(startX, startY, stopX, stopY, paint);
				}
			} else if (staff_unit_sencodary_tag_space_num >= 0 && t % staff_unit_sencodary_tag_space_num == 0) {
				if (isIs_herizontal()) {
					canvas.drawLine(x, 0 + paint_staff.getStrokeWidth(), x,
							0 + paint_staff.getStrokeWidth() + staff_secondary_unit_tag_line_size,
							paint_sencodary_tag_unit);
				} else {
					canvas.drawLine(0 + paint_staff.getStrokeWidth(), x,
							0 + paint_staff.getStrokeWidth() + staff_secondary_unit_tag_line_size, x,
							paint_sencodary_tag_unit);
				}
			} else {
				if (isIs_herizontal()) {
					canvas.drawLine(x, 0 + paint_staff.getStrokeWidth(), x,
							0 + paint_staff.getStrokeWidth() + staff_unit_line_size, paint_unit);
				} else {
					canvas.drawLine(0 + paint_staff.getStrokeWidth(), x,
							0 + paint_staff.getStrokeWidth() + staff_unit_line_size, x, paint_unit);
				}
			}
		}

	}

	/**
	 * 用于配置刻度标签名的函数（可重载）
	 */
	private GetUnitTagName getUnitTagName = new GetUnitTagName() {

		@Override
		public String get(int start, int end, int index, int count) {
			// TODO Auto-generated method stub
			return (isReverseOrder() ? (end - index) : (index + start)) + "";
		}
	};

	/**
	 * 用于配置刻度标签名的函数（可重载）
	 * 
	 * @author yanning<br>
	 *         2016年3月21日上午1:31:43<br>
	 */
	public interface GetUnitTagName {
		public String get(int start, int end, int index, int count);
	}

	/**** start:setter and getter *****/
	/**
	 * 
	 * @author yanning<br>
	 *         2016年3月20日下午10:03:53<br>
	 * @param staff_start
	 */
	public void setStaff_start(int staff_start) {
		this.staff_start = staff_start;
	}

	public void setStaff_end(int staff_end) {
		this.staff_end = staff_end;
	}

	public boolean isIs_herizontal() {
		return is_herizontal;
	}

	public void setIs_herizontal(boolean is_herizontal) {
		this.is_herizontal = is_herizontal;
	}

	public int getStaff_unit_value() {
		return staff_unit_value;
	}

	public void setStaff_unit_value(int staff_unit_value) {
		this.staff_unit_value = staff_unit_value;
	}

	public String getStaff_unit_name() {
		return staff_unit_name;
	}

	public void setStaff_unit_name(String staff_unit_name) {
		if (staff_unit_name == null)
			staff_unit_name = "";
		this.staff_unit_name = staff_unit_name;
	}

	public int getStaff_line_width() {
		return staff_line_width;
	}

	public void setStaff_line_width(int staff_line_width) {
		this.staff_line_width = staff_line_width;
	}

	public int getStaff_unit_line_width() {
		return staff_unit_line_width;
	}

	public void setStaff_unit_line_width(int staff_unit_line_width) {
		this.staff_unit_line_width = staff_unit_line_width;
	}

	public int getStaff_unit_tag_line_width() {
		return staff_unit_tag_line_width;
	}

	public void setStaff_unit_tag_line_width(int staff_unit_tag_line_width) {
		this.staff_unit_tag_line_width = staff_unit_tag_line_width;
	}

	public int getStaff_unit_line_size() {
		return staff_unit_line_size;
	}

	public void setStaff_unit_line_size(int staff_unit_line_size) {
		this.staff_unit_line_size = staff_unit_line_size;
	}

	public int getStaff_unit_tag_line_size() {
		return staff_unit_tag_line_size;
	}

	public void setStaff_unit_tag_line_size(int staff_unit_tag_line_size) {
		this.staff_unit_tag_line_size = staff_unit_tag_line_size;
	}

	public int getStaff_unit_line_color() {
		return staff_unit_line_color;
	}

	public void setStaff_unit_line_color(int staff_unit_line_color) {
		this.staff_unit_line_color = staff_unit_line_color;
	}

	public int getStaff_unit_tag_textColor() {
		return staff_unit_tag_textColor;
	}

	public void setStaff_unit_tag_textColor(int staff_unit_tag_textColor) {
		this.staff_unit_tag_textColor = staff_unit_tag_textColor;
	}

	public int getStaff_unit_tag_textSize() {
		return staff_unit_tag_textSize;
	}

	public void setStaff_unit_tag_textSize(int staff_unit_tag_textSize) {
		this.staff_unit_tag_textSize = staff_unit_tag_textSize;
	}

	public Paint getPaint_text() {
		return paint_text;
	}

	public void setPaint_text(Paint paint_text) {
		this.paint_text = paint_text;
	}

	public Paint getPaint_staff() {
		return paint_staff;
	}

	public void setPaint_staff(Paint paint_staff) {
		this.paint_staff = paint_staff;
	}

	public int getStaff_start() {
		return staff_start;
	}

	public int getStaff_end() {
		return staff_end;
	}

	public int getStaff_unit_tag_space_num() {
		return staff_unit_tag_space_num;
	}

	public void setStaff_unit_tag_space_num(int staff_unit_tag_space_num) {
		this.staff_unit_tag_space_num = staff_unit_tag_space_num;
	}

	public int getStaff_unit_sencodary_tag_space_num() {
		return staff_unit_sencodary_tag_space_num;
	}

	public void setStaff_unit_sencodary_tag_space_num(int staff_unit_sencodary_tag_space_num) {
		this.staff_unit_sencodary_tag_space_num = staff_unit_sencodary_tag_space_num;
	}

	public int getStaff_unit_sencodary_tag_line_width() {
		return staff_unit_sencodary_tag_line_width;
	}

	public void setStaff_unit_sencodary_tag_line_width(int staff_unit_sencodary_tag_line_width) {
		this.staff_unit_sencodary_tag_line_width = staff_unit_sencodary_tag_line_width;
	}

	public int getStaff_secondary_unit_tag_line_size() {
		return staff_secondary_unit_tag_line_size;
	}

	public void setStaff_secondary_unit_tag_line_size(int staff_secondary_unit_tag_line_size) {
		this.staff_secondary_unit_tag_line_size = staff_secondary_unit_tag_line_size;
	}

	public Paint getPaint_unit() {
		return paint_unit;
	}

	public void setPaint_unit(Paint paint_unit) {
		this.paint_unit = paint_unit;
	}

	public Paint getPaint_tag_unit() {
		return paint_tag_unit;
	}

	public void setPaint_tag_unit(Paint paint_tag_unit) {
		this.paint_tag_unit = paint_tag_unit;
	}

	public Paint getPaint_sencodary_tag_unit() {
		return paint_sencodary_tag_unit;
	}

	public void setPaint_sencodary_tag_unit(Paint paint_sencodary_tag_unit) {
		this.paint_sencodary_tag_unit = paint_sencodary_tag_unit;
	}

	public Paint getPaint_blank() {
		return paint_blank;
	}

	public void setPaint_blank(Paint paint_blank) {
		this.paint_blank = paint_blank;
	}

	public GetUnitTagName getGetUnitTagName() {
		return getUnitTagName;
	}

	public void setGetUnitTagName(GetUnitTagName getUnitTagName) {
		this.getUnitTagName = getUnitTagName;
	}

	public boolean isReverseOrder() {
		return isReverseOrder;
	}

	public void setReverseOrder(boolean isReverseOrder) {
		this.isReverseOrder = isReverseOrder;
	}

	/**** end:setter and getter *****/
	/**
	 * 获取标尺的单位下总长度
	 * 
	 * @author yanning<br>
	 *         2016年5月16日上午10:48:47<br>
	 * @return
	 */
	public int getStaffLengthWithUnit() {
		return Math.abs(Integer.valueOf(getStaff_start())) + Math.abs(Integer.valueOf(getStaff_end()));
	}
}
