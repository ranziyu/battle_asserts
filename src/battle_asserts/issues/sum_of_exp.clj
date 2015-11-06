(ns battle-asserts.issues.sum-of-exp
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :medium)

(def description "#FIXME Напишите функцию принимающую на вход целое число x и возвращающую
                 сумму x + xx + xxx + ... (x раз) в виде строки")

(defn arguments-generator []
  (gen/tuple (gen/choose 1 25)))

(def test-data
  [{:expected "369"
    :arguments [3]}
   {:expected "61725"
    :arguments [5]}
   {:expected "740736"
    :arguments [6]}
   {:expected "98765424"
    :arguments [8]}])

(defn sum-strings [string1 strign2]
  (loop [summand1 (map str (reverse string1))
         summand2 (map str (reverse strign2))
         overflow 0
         result ""]
    (if (and (empty? summand1) (empty? summand2))
      result
      (let [number1 (read-string (or (first summand1) "0"))
            number2 (read-string (or (first summand2) "0"))
            sum (+ number1 number2 overflow)]
        (recur (rest summand1)
               (rest summand2)
               (quot sum 10)
               (str (rem sum 10)
                    result))))))

(defn solution [number]
  (->>
   (range 1 (inc number))
   (map #(s/join (repeat % number)))
   (reduce sum-strings)))
