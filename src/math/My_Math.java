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
        /*
            Let's go through each line:
            At the first line below (62) we check, if we want to compare the distance between all shapes.
                If not, we check if we compare the correct shape
            At the next line (63) we start the stream. Therefore we go through all INSTANCES of the specific shape.
                The end-result of the stream is the specific shape object (in the first block it is Own_Rect).
                If the stream doesn't find an object which is correct, result will be set to null (because of orElse(null))
            Then (64) we filter the specific shapes by the condition after the lambda expression. The lambda expression works
            as follows:
                The variable own_rect is used to store each element from the INSTANCES ArrayList. So the expression inside the
                filter clause will be repeated as many times as the ArrayList INSTANCES size is. After the lambda expression (->)
                a boolean value is expected. For each own_rect it will be determind, if it should be inside the following streaming
                functions (boolean value = true) or if it should be filtered out (boolean value = false)

            :
                    (!(shape instanceof Own_Rect)) =>       If the shape is no instance of the Own_Rect, all elements
                                                            from the INSTANCES ArrayList will be taken. If it is an instance
                                                            of the Own_Rect, we check the other condition:
                    !own_rect.equal((Own_Rect) shape) =>    Now, that we now, it is an instance of Own_rect, we can cast
                                                            the shape to (Own_Rect) and compare it with each element inside
                                                            the INSTANCES ArrayList. If they are equal, it will be filtered
                                                            out. Otherwise, the closest shape to the shape would be itself.

            After we filtered the elements we start a new loop with the rest of the elements in the next line (65). In this line
            we use the min() streaming function. This expects either a lambda expression with two parameters ((first, second) ->)
            were the return type is an integer which determines, if the first value will be kept or if the second value will
            be kept. (return value <= 0 => first,    return value > 0 => second). If we would write
            ((first, second) -> -1)) the first element would be the only one which will sustain inside the list.
            But in this case we use a Comparator. With the Comparator we compare the double Values of the distance between
            the shape and each remaining element inside the ArrayList INSTANCES.
            At the last line, we get either the closest rect out of the remaining stream or null.
         */
        if(all_shapes || shape.getClass().getName().equalsIgnoreCase("shape.own_rect")){
            result = Own_Rect.INSTANCES.stream()
                    .filter(own_rect -> (!(shape instanceof Own_Rect)) || !own_rect.equal((Own_Rect) shape))
                    .min(Comparator.comparingDouble(own_rect -> getSquareDistanceBetween(own_rect,shape)))
                    .orElse(null);
        }

        if(all_shapes || shape.getClass().getName().equalsIgnoreCase("shape.own_triangle")){
            Object tmp = Own_Triangle.INSTANCES.stream()
                    .filter(own_triangle -> (!(shape instanceof Own_Triangle)) || !own_triangle.equal((Own_Triangle) shape))
                    .min(Comparator.comparingDouble(own_triangle -> getSquareDistanceBetween(own_triangle,shape)))
                    .orElse(null);

            if(result == null) result = tmp;
            if(tmp != null && getSquareDistanceBetween(tmp,shape) <= getSquareDistanceBetween(result,shape)) result = tmp;
        }
        if(all_shapes || shape.getClass().getName().equalsIgnoreCase("shape.own_ellipse")){
            Object tmp = Own_Ellipse.INSTANCES.stream()
                    .filter(own_ellipse ->  (!(shape instanceof Own_Ellipse)) || !own_ellipse.equal((Own_Ellipse) shape))
                    .min(Comparator.comparingDouble(own_ellipse -> getSquareDistanceBetween(own_ellipse,shape)))
                    .orElse(null);
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
