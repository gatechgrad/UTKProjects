VERSION 5.00
Begin VB.Form frmICAESV 
   BackColor       =   &H00E0E0E0&
   Caption         =   "Initial Cost / Annual Expenses / Salvage Value"
   ClientHeight    =   4920
   ClientLeft      =   60
   ClientTop       =   345
   ClientWidth     =   5220
   LinkTopic       =   "Form1"
   ScaleHeight     =   4920
   ScaleWidth      =   5220
   StartUpPosition =   2  'CenterScreen
   Begin VB.TextBox txtEL 
      Height          =   375
      Left            =   1560
      TabIndex        =   1
      Top             =   720
      Width           =   975
   End
   Begin VB.CommandButton butResult 
      Caption         =   "Results"
      Height          =   375
      Left            =   120
      TabIndex        =   5
      Top             =   3360
      Width           =   1335
   End
   Begin VB.TextBox txtEUAC 
      BackColor       =   &H00E0E0E0&
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   2640
      Locked          =   -1  'True
      TabIndex        =   6
      Top             =   3360
      Width           =   1695
   End
   Begin VB.TextBox txtSV 
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   2280
      TabIndex        =   4
      Top             =   2640
      Width           =   1455
   End
   Begin VB.TextBox txtInterest 
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   1560
      TabIndex        =   0
      Top             =   240
      Width           =   855
   End
   Begin VB.TextBox txtAE 
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   2280
      TabIndex        =   3
      Top             =   2160
      Width           =   1455
   End
   Begin VB.TextBox txtIC 
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   2280
      TabIndex        =   2
      Top             =   1680
      Width           =   1455
   End
   Begin VB.CommandButton butCancel 
      Caption         =   "Cancel"
      Height          =   375
      Left            =   1440
      TabIndex        =   9
      Top             =   4320
      Width           =   1215
   End
   Begin VB.CommandButton butOK 
      Caption         =   "OK"
      Height          =   375
      Left            =   120
      TabIndex        =   7
      Top             =   4320
      Width           =   1095
   End
   Begin VB.Label Label7 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Economic Life:"
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   240
      TabIndex        =   15
      Top             =   720
      Width           =   1215
   End
   Begin VB.Label Label6 
      BackColor       =   &H00E0E0E0&
      Caption         =   "EUAC:"
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   1920
      TabIndex        =   14
      Top             =   3360
      Width           =   855
   End
   Begin VB.Label Label5 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Salvage Value (SV) at End of Economic Life:"
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   240
      TabIndex        =   13
      Top             =   2640
      Width           =   1575
   End
   Begin VB.Label Label4 
      BackColor       =   &H00E0E0E0&
      Caption         =   "%"
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   2520
      TabIndex        =   12
      Top             =   240
      Width           =   615
   End
   Begin VB.Label Label3 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Interest Rate:"
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   240
      TabIndex        =   11
      Top             =   240
      Width           =   1215
   End
   Begin VB.Label Label2 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Annual Expenses  (AE): "
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   240
      TabIndex        =   10
      Top             =   2160
      Width           =   1815
   End
   Begin VB.Label Label1 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Initial Cost:"
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   240
      TabIndex        =   8
      Top             =   1680
      Width           =   975
   End
End
Attribute VB_Name = "frmICAESV"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Private Sub butCancel_Click()
    Unload Me
End Sub

Private Sub butOK_Click()
    If (txtEUAC.Text <> "") Then
    
        If (MainForm.rdoDef.Value = True) Then
            MainForm.txtDefMinEUAC.Text = txtEUAC.Text
        ElseIf (MainForm.rdoChal.Value = True) Then
            MainForm.txtChalMinEUAC.Text = txtEUAC.Text
        End If
    
        Unload Me
    
    
    Else
        MsgBox "No EUAC Calculated", vbInformation
    End If
    
End Sub

Private Sub butResult_Click()
    Dim iEUAC As Currency
    Dim iInterest As Double
    Dim iYears As Double
    Dim iInitialCost As Currency
    Dim iAnnualExpenses As Currency
    Dim iSalvageValue As Currency
    
    If (IsNumeric(txtEL.Text) And IsNumeric(txtInterest.Text) And IsNumeric(txtIC.Text) And IsNumeric(txtAE.Text) And IsNumeric(txtSV.Text)) Then
        iInterest = txtInterest.Text
        iInterest = iInterest / 100#
        iYears = txtEL.Text
        iInitialCost = txtIC.Text
        iAnnualExpenses = txtAE.Text
        iSalvageValue = txtSV.Text
        
        iEUAC = (iInitialCost * Constants.AGivenP(iInterest, iYears)) - (iSalvageValue * Constants.AGivenF(iInterest, iYears)) + iAnnualExpenses
        txtEUAC.Text = Format(iEUAC, "0.00")
    
    Else
        MsgBox "Please enter numeric values for Interest Rate, Initial Cost, Annual Expenses, and Salvage Value", vbInformation
    End If
End Sub
