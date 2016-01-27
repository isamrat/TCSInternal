(ns TCSInternal.service
  (:require [dk.ative.docjure.spreadsheet :as spreadsheet]))

(defn vpn-search [empn]
  (def data (->>
              (spreadsheet/load-workbook "abc.xls")
              (spreadsheet/select-sheet "Active")
              (spreadsheet/select-columns {:A :Emp# :C :VPN})))

  (def empno (Integer/parseInt empn))
  (def vpn nil)
  (def flag (atom 0))
  (doseq [x data]
    (def emp (get x :Emp#))
    (if-not(string? emp) (def emp (int emp)))
    (swap! flag inc)
    (if(= emp empno)
      (do
        (def vpn (int (get x :VPN)))
        ;(println ((String) vpn))
        ))
    )
  vpn
  )