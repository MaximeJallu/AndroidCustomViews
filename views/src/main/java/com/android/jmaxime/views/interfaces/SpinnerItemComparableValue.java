package com.android.jmaxime.views.interfaces;

/**
 * @author Maxime Jallu
 * @since 01/09/2016
 *
 * Create for CubeInStore - Android (Decathlon)
 *
 * Use this Class for : <br/>
 * if you use @link EditTextSpinner and you set default value before set adapter,
 * this interface is util for found your default value<br/><br/>
 *
 * <code>
 *     set default value : Bonjour<br/>
 *
 *     after set List<String>{ "Hello" , "Hi" , "Bj" , "Hola"} <br/>
 *
 *     boolean isMatchValue(String valueCompare){
 *         return this.getLabel.equals("Bj") && valueCompare.equals("Bonjour") ;
 *     }
 * </code>
 * With this interface return Matching complex values
 *
 * @link SpinnerDialogItem
 * @link EditTextSpinner
 */
public interface SpinnerItemComparableValue {

    boolean isMatchValue(String valueCompare);
}
