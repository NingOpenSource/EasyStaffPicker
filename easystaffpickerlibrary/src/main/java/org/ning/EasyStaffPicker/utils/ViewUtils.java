package org.ning.EasyStaffPicker.utils;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author yanni
 *
 */
public class ViewUtils {

	/**
	 * 
	 * @author yanning<br>
	 *         2016年6月27日下午2:38:05<br>
	 * @param v
	 * @return
	 */
	@SuppressLint("NewApi")
	public static int getMinimumWidth(View v) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			return v.getMinimumWidth();
		} else {
			try {
				Method method = v.getClass().getDeclaredMethod("getSuggestedMinimumWidth");
				return (int) method.invoke(v);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}
	}


	@SuppressLint("NewApi")
	public static int getMinimumHeight(View v) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			return v.getMinimumHeight();
		} else {
			try {
				Method method = v.getClass().getDeclaredMethod("getSuggestedMinimumHeight");
				return (int) method.invoke(v);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}
	}

}
