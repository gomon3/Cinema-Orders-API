package com.cinema.challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * Calcula el máximo explorando subconjuntos de combos.
     *
     * @param purchased productos comprados por el cliente
     * @param combos    cada combo es un array de productos requeridos
     * @param discounts descuento correspondiente a cada combo
     * @return descuento total máximo aplicable
     */
    public int maxDiscount(String[] purchased, String[][] combos, int[] discounts) {
        Map<String, Integer> available = new HashMap<>();
        for (String product : purchased) {
            available.merge(product, 1, Integer::sum);
        }
        List<Map<String, Integer>> requirements = new ArrayList<>();
        for (String[] combo : combos) {
            Map<String, Integer> counts = new HashMap<>();
            for (String product : combo) {
                counts.merge(product, 1, Integer::sum);
            }
            requirements.add(counts);
        }
        return search(0, available, requirements, discounts);
    }

    private int search(int index, Map<String, Integer> available,
                       List<Map<String, Integer>> combos, int[] discounts) {
        if (index == combos.size()) {
            return 0;
        }
        int best = search(index + 1, available, combos, discounts);
        Map<String, Integer> needed = combos.get(index);
        boolean fits = needed.entrySet().stream()
                .allMatch(entry -> available.getOrDefault(entry.getKey(), 0) >= entry.getValue());
        if (fits) {
            needed.forEach((product, count) -> available.merge(product, -count, Integer::sum));
            best = Math.max(best, discounts[index] + search(index + 1, available, combos, discounts));
            needed.forEach((product, count) -> available.merge(product, count, Integer::sum));
        }
        return best;
    }
}
