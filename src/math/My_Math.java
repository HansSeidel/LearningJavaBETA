package math;

import shape.Own_Ellipse;
import shape.Own_Rect;
import shape.Own_Triangle;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.Optional;

public class My_Math {

    /**
     * Returns the closest object or null.
     * @param shape
     * @return
     */
    public static Object getClosestShape(Object shape) {
        return getClosestShape(shape,false);
    }

    /**
     * Returns the closest object or null. Able to check for different shapes as well
     * @param shape
     * @param all_shapes
     * @return
     */
    public static Object getClosestShape(Object shape, boolean all_shapes) {
        Object result = null;

        //At the first line below we check, if we want to compare the distance between all shapes.
        //  If not, we check if we compare the correct shape
        if(all_shapes || shape.getClass().getName().equalsIgnoreCase("shape.own_rect")){
            //At the next line we start the loop. Therefore we go through all INSTANCES of the specific shape.
            for(Own_Rect own_rect : Own_Rect.INSTANCES){
                //Then we check if we currently looking at the instance of the shape we want to get the closest shape to.
                //  If so, we chang is_self to true
                boolean is_self = false;
                if(shape instanceof Own_Rect){
                    if(own_rect.equal((Own_Rect) shape)){
                        is_self = true;
                    }
                }
                //If we are not looking at the instance of the shape itself, we can continue. Otherwise nothing will happen.
                if(!is_self){
                    if(result == null)result = own_rect;
                    //Now we compare the distance between the current element (own_rect) to the shape, with the distance of the
                    // current closest element (result) to the shape. If the current element is closer, we change the
                    // result to the current element.
                    if(own_rect != null && getSquareDistanceBetween(own_rect,shape) <= getSquareDistanceBetween(result,shape))
                        result = own_rect;
                }
            }
        }

        if(all_shapes || shape.getClass().getName().equalsIgnoreCase("shape.own_triangle")){
            Object tmp = null;
            for(Own_Triangle own_triangle : Own_Triangle.INSTANCES){
                boolean is_self = false;
                if(shape instanceof Own_Triangle){
                    if(own_triangle.equal((Own_Triangle) shape)){
                        is_self = true;
                    }
                }
                if(!is_self){
                    if(tmp == null)tmp = own_triangle;
                    if(own_triangle != null && getSquareDistanceBetween(own_triangle,shape) <= getSquareDistanceBetween(tmp,shape))
                        tmp = own_triangle;
                }
            }
            //These two lines are only necessary, if all shapes is true.
            if(result == null) result = tmp;
            if(tmp != null && getSquareDistanceBetween(tmp,shape) <= getSquareDistanceBetween(result,shape)) result = tmp;
        }
        if(all_shapes || shape.getClass().getName().equalsIgnoreCase("shape.own_ellipse")){
            Object tmp = null;
            for(Own_Ellipse own_ellipse : Own_Ellipse.INSTANCES){
                boolean is_self = false;
                if(shape instanceof Own_Ellipse){
                    if(own_ellipse.equal((Own_Ellipse) shape)){
                        is_self = true;
                    }
                }
                if(!is_self){
                    if(tmp == null)tmp = own_ellipse;
                    if(own_ellipse != null && getSquareDistanceBetween(own_ellipse,shape) <= getSquareDistanceBetween(tmp,shape))
                        tmp = own_ellipse;
                }
            }
            //These two lines are only necessary, if all shapes is true.
            if(result == null) result = tmp;
            if(tmp != null && getSquareDistanceBetween(tmp,shape) <= getSquareDistanceBetween(result,shape)) result = tmp;
        }
        return result;
    }

    private static double getSquareDistanceBetween(Object o1, Object o2) {
        try {
            Point p_o1 = (Point) o1.getClass().getMethod("getCenter").invoke(o1);
            Point p_o2 = (Point) o2.getClass().getMethod("getCenter").invoke(o2);
            return (Math.pow(p_o1.getX()-p_o2.getX(),2)+Math.pow(p_o1.getY()-p_o2.getY(),2));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static double getDistanceBetween(Object o1, Object o2) {
        return Math.sqrt(getSquareDistanceBetween(o1,o2));
    }
}
