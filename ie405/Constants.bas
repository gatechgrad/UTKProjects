Attribute VB_Name = "Constants"
Public Function FGivenP(iInterest As Double, iYears As Double)
    Dim dReturn As Double
    
    dReturn = (1 + iInterest) ^ iYears
    FGivenP = dReturn
End Function


Public Function PGivenF(iInterest As Double, iYears As Double)
    Dim dReturn As Double
    
    dReturn = 1# + iInterest
    dReturn = dReturn ^ iYears
    dReturn = 1# / dReturn
    
    PGivenF = dReturn

End Function
Public Function PGivenA(iInterest As Double, iYears As Double)
    Dim dReturn As Double
    
    dReturn = (((1 + iInterest) ^ iYears) - 1) / (iInterest * ((1 + iInterest) ^ iYears))
    PGivenA = dReturn
End Function


Public Function AGivenP(iInterest As Double, iYears As Double)
    Dim dReturn As Double
    
    dReturn = (iInterest * ((1# + iInterest) ^ iYears)) / (((1# + iInterest) ^ iYears) - 1#)
    AGivenP = dReturn
End Function

Public Function FGivenA(iInterest As Double, iYears As Double)
    Dim dReturn As Double
    
    dReturn = (((1 + iInterest) ^ iYears) - 1) / iInterest
    FGivenA = dReturn
End Function

Public Function AGivenF(iInterest As Double, iYears As Double)
    Dim dReturn As Double
    
    dReturn = iInterest / (((1 + iInterest) ^ iYears) - 1)
    AGivenF = dReturn
End Function


Public Function PGivenG(iInterest As Double, iYears As Double)
    Dim dReturn As Double
    
    dReturn = (((1 + iInterest) ^ iYears) - (iInterest * iYears) - 1) / ((iInterest ^ 2) * ((1 + iInterest) ^ iYears))
    PGivenG = dReturn
End Function

Public Function AGivenG(iInterest As Double, iYears As Double)
    Dim dReturn As Double
    
    dReturn = (1 / iInterest) - (iYears / (((1 + iInterest) ^ iYears) - 1))
    AGivenG = dReturn
End Function

