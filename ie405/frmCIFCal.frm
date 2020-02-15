VERSION 5.00
Begin VB.Form frmCIFCal 
   BackColor       =   &H00E0E0E0&
   Caption         =   "Compound Interest Factor Calculator"
   ClientHeight    =   3090
   ClientLeft      =   60
   ClientTop       =   375
   ClientWidth     =   4650
   LinkTopic       =   "Form1"
   ScaleHeight     =   3090
   ScaleWidth      =   4650
   StartUpPosition =   2  'CenterScreen
   Begin VB.CommandButton butResult 
      Caption         =   "Result"
      Height          =   495
      Left            =   2520
      TabIndex        =   6
      Top             =   2160
      Width           =   1095
   End
   Begin VB.TextBox txtPeriod 
      BackColor       =   &H00FFFFFF&
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   2880
      TabIndex        =   5
      Top             =   1560
      Width           =   615
   End
   Begin VB.TextBox txtInterest 
      BackColor       =   &H00FFFFFF&
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   2760
      TabIndex        =   2
      Top             =   960
      Width           =   855
   End
   Begin VB.ComboBox cmbFormula 
      BackColor       =   &H00FFFFFF&
      ForeColor       =   &H00000000&
      Height          =   315
      ItemData        =   "frmCIFCal.frx":0000
      Left            =   360
      List            =   "frmCIFCal.frx":001C
      TabIndex        =   0
      Top             =   360
      Width           =   2415
   End
   Begin VB.Label Label3 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Number of Interest Periods (n):"
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   360
      TabIndex        =   4
      Top             =   1560
      Width           =   2415
   End
   Begin VB.Label Label2 
      BackColor       =   &H00E0E0E0&
      Caption         =   "%"
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   3840
      TabIndex        =   3
      Top             =   960
      Width           =   375
   End
   Begin VB.Label Label1 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Interest (i):"
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   360
      TabIndex        =   1
      Top             =   960
      Width           =   735
   End
End
Attribute VB_Name = "frmCIFCal"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Private Sub butResult_Click()
    Dim iInterest As Double
    Dim iYears As Double

    If (txtInterest.Text <> "" And txtPeriod.Text <> "" And IsNumeric(txtInterest.Text) And IsNumeric(txtPeriod.Text)) Then
      iInterest = txtInterest.Text
      iInterest = iInterest / 100#
      iYears = txtPeriod.Text
      
      Select Case Left(cmbFormula.Text, 3):
        Case "F/P"
            InputBox "(F/P, " & txtInterest.Text & "%, " & txtPeriod.Text & ")", "Result", Format(Constants.FGivenP(iInterest, iYears), "0.0000")
        Case "P/F"
            InputBox "(P/F, " & txtInterest.Text & "%, " & txtPeriod.Text & ")", "Result", Format(Constants.PGivenF(iInterest, iYears), "0.0000")
        Case "A/F"
            InputBox "(A/F, " & txtInterest.Text & "%, " & txtPeriod.Text & ")", "Result", Format(Constants.AGivenF(iInterest, iYears), "0.00000")
        Case "F/A"
            InputBox "(F/A, " & txtInterest.Text & "%, " & txtPeriod.Text & ")", "Result", Format(Constants.FGivenA(iInterest, iYears), "0.0000")
        Case "A/P"
            InputBox "(A/P, " & txtInterest.Text & "%, " & txtPeriod.Text & ")", "Result", Format(Constants.AGivenP(iInterest, iYears), "0.00000")
        Case "P/A"
            InputBox "(P/A, " & txtInterest.Text & "%, " & txtPeriod.Text & ")", "Result", Format(Constants.PGivenA(iInterest, iYears), "0.0000")
        Case "P/G"
            InputBox "(P/G, " & txtInterest.Text & "%, " & txtPeriod.Text & ")", "Result", Format(Constants.PGivenG(iInterest, iYears), "0.0000")
        Case "A/G"
            InputBox "(A/G, " & txtInterest.Text & "%, " & txtPeriod.Text & ")", "Result", Format(Constants.AGivenG(iInterest, iYears), "0.0000")

        Case Else
            MsgBox "Please select a formula from the dropdown list", vbInformation
      End Select
    Else
        MsgBox "Please Enter valid interest and period", vbInformation
    End If
        
    
    
End Sub
