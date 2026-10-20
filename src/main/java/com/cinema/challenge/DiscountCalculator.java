package com.cinema.challenge;

/**
 * Ejercicio: Descuento por Combo
 *
 * Una cadena de cines tiene una regla de combos: si un cliente compra ciertos
 * productos juntos, recibe un descuento. Dado un listado de productos comprados
 * y las reglas de combo, calcula el descuento total máximo que aplica.
 *
 * Reglas:
 * - Cada combo puede aplicarse UNA sola vez
 * - Un producto solo puede pertenecer a un combo (no se reutiliza)
 * - Se busca el descuento MÁXIMO posible
 *
 * Ejemplo 1:
 *   purchased = ["popcorn", "soda", "nachos", "popcorn", "soda"]
 *   combos = [["popcorn", "soda"], ["nachos", "soda"]]
 *   discounts = [20, 15]
 *   Output: 35
 *
 * Ejemplo 2:
 *   purchased = ["soda"]
 *   combos = [["popcorn", "soda"]]
 *   discounts = [20]
 *   Output: 0 (no tiene popcorn, no aplica el combo)
 *
 * Constraints:
 *   1 <= purchased.length <= 50
 *   1 <= combos.length <= 10
 *   1 <= combos[i].length <= 5
 *   discounts.length == combos.length
 */
public class DiscountCalculator {

    /**
     * TODO: Implementar
     *
     * @param purchased productos comprados por el cliente
     * @param combos    cada combo es un array de productos requeridos
     * @param discounts descuento correspondiente a cada combo
     * @return descuento total máximo aplicable
     */
    public int maxDiscount(String[] purchased, String[][] combos, int[] discounts) {
        // Tu implementación aquí
        return 0;
    }
}
