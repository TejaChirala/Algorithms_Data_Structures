import extensions.print
import kotlin.math.min
class Solution {

    var min = Integer.MAX_VALUE

    fun coinChange(coins: IntArray, a: Int): Int {
        return getCoins(coins, a, 0, HashMap<Int, Int>())
    }

    private fun getCoins(coins: IntArray, amount: Int, coinsCount: Int,
                         map: HashMap<Int, Int>): Int {
        if (amount == 0) {
            //min = min(min, coinsCount)
            return coinsCount
        }

        var curMin = Integer.MAX_VALUE
        coins.forEach { d ->
            if (amount >= d) {
                val diff = amount - d
                val count = if (map.containsKey(diff)) {
                    map[diff]!!
                } else {
                    getCoins(coins, diff, coinsCount + 1, map)
                }
                curMin = min(curMin, count)
            }
        }
        map[amount] = curMin
        return curMin
    }
}
fun main() {
    Solution().apply {
        coinChange(intArrayOf(1,2,5), 11).print()
    }
}