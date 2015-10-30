(ns battle-asserts.issues.sum-primes
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :medium)

(def description "Write some code to sum all primes from 2 up to a given number.")

(defn arguments-generator []
  (gen/tuple (gen/choose 10 1000)))

(def test-data
  [{:expected 4227
    :arguments [200]}
   {:expected 76127
    :arguments [1000]}])

(defn solution [num]
  (reduce + (take-while (partial > num)
                        (iterate #(.nextProbablePrime (biginteger %)) 2))))