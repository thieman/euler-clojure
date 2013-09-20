(ns euler.batch1)

(defn is-multiple? [n xs]
  (some #(= (clojure.core/rem n %) 0) xs))

(defn p1
  "If we list all the natural numbers below 10 that are multiples of 3
   or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.

   Find the sum of all the multiples of 3 or 5 below 1000."
  []
  (reduce + (filter #(is-multiple? % [3 5]) (range 1 1000))))

;; (println (p1))

(defn fibonacci [max]
  (loop [p0 1 p1 2 acc [1 2]]
    (let [val (+ p0 p1)]
      (if (> val max)
        acc
        (recur p1 val (conj acc val))))))

(defn p2
  "Each new term in the Fibonacci sequence is generated by adding the
   previous two terms. By starting with 1 and 2, the first 10 terms will be:

   1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...

   By considering the terms in the Fibonacci sequence whose values do not
   exceed four million, find the sum of the even-valued terms."
  []
  (let [target 4000000]
    (reduce + (filter even? (fibonacci target)))))

;; (println (p2))

(defn remove-multiples [max xs x]
  (let [multiples (range (* x 2) max x)]
    (apply disj xs multiples)))

(defn primes
  "Generate vector of primes up to given maximum using Sieve of Erastothenes."
  [max]
  (let [rem (set (range 1 max))]
    (apply vector (sort (reduce (partial remove-multiples max) rem (range 2 max))))))

(defn p3
  "The prime factors of 13195 are 5, 7, 13 and 29.

  What is the largest prime factor of the number 600851475143 ?"
  []
  (let [target 600851475143
        p (primes (Math/sqrt target))
        is-factor? #(zero? (clojure.core/rem %1 %2))]
    (apply max (filter (partial is-factor? target) p))))

;; (println (p3))
