VERSION 5.00
Begin VB.Form frmCIFCal 
   BackColor       =   &H00E0E0E0&
   Caption         =   "Interest Calculator"
   ClientHeight    =   5370
   ClientLeft      =   60
   ClientTop       =   375
   ClientWidth     =   5685
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   MDIChild        =   -1  'True
   ScaleHeight     =   5370
   ScaleWidth      =   5685
   Begin VB.CommandButton butAG 
      Caption         =   "A/G"
      Height          =   495
      Left            =   1080
      TabIndex        =   24
      TabStop         =   0   'False
      ToolTipText     =   "Gradient Uniform Series"
      Top             =   4680
      Width           =   615
   End
   Begin VB.CommandButton butPG 
      Caption         =   "P/G"
      Height          =   495
      Left            =   360
      TabIndex        =   23
      TabStop         =   0   'False
      ToolTipText     =   "Gradient Present Worth"
      Top             =   4680
      Width           =   615
   End
   Begin VB.CommandButton butClear 
      Caption         =   "Clear"
      Height          =   495
      Left            =   3120
      TabIndex        =   22
      TabStop         =   0   'False
      Top             =   2280
      Width           =   975
   End
   Begin VB.CommandButton butBack 
      Caption         =   "Back"
      Height          =   495
      Left            =   2040
      TabIndex        =   21
      TabStop         =   0   'False
      Top             =   2280
      Width           =   975
   End
   Begin VB.TextBox txtMessage 
      BackColor       =   &H00C0FFC0&
      BeginProperty Font 
         Name            =   "Terminal"
         Size            =   9
         Charset         =   255
         Weight          =   400
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      ForeColor       =   &H00004000&
      Height          =   375
      Left            =   360
      Locked          =   -1  'True
      TabIndex        =   20
      TabStop         =   0   'False
      Top             =   1560
      Width           =   4935
   End
   Begin VB.CommandButton butAF 
      Caption         =   "A/F"
      Height          =   495
      Left            =   360
      TabIndex        =   19
      TabStop         =   0   'False
      ToolTipText     =   "Sinking Fund"
      Top             =   3480
      Width           =   615
   End
   Begin VB.CommandButton butFA 
      Caption         =   "F/A"
      Height          =   495
      Left            =   1080
      TabIndex        =   18
      TabStop         =   0   'False
      ToolTipText     =   "Compound Amount"
      Top             =   3480
      Width           =   615
   End
   Begin VB.CommandButton butAP 
      Caption         =   "A/P"
      Height          =   495
      Left            =   360
      TabIndex        =   17
      TabStop         =   0   'False
      ToolTipText     =   "Capital Recovery"
      Top             =   4080
      Width           =   615
   End
   Begin VB.CommandButton butPA 
      Caption         =   "P/A"
      Height          =   495
      Left            =   1080
      TabIndex        =   16
      TabStop         =   0   'False
      ToolTipText     =   "Present Worth"
      Top             =   4080
      Width           =   615
   End
   Begin VB.TextBox txtScreen 
      BackColor       =   &H00C0FFC0&
      BeginProperty Font 
         Name            =   "Terminal"
         Size            =   9
         Charset         =   255
         Weight          =   400
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      ForeColor       =   &H00004000&
      Height          =   1455
      Left            =   360
      Locked          =   -1  'True
      MultiLine       =   -1  'True
      ScrollBars      =   2  'Vertical
      TabIndex        =   15
      Top             =   120
      Width           =   5175
   End
   Begin VB.CommandButton butPF 
      Caption         =   "P/F"
      Height          =   495
      Left            =   1080
      TabIndex        =   14
      TabStop         =   0   'False
      ToolTipText     =   "Present Worth"
      Top             =   2880
      Width           =   615
   End
   Begin VB.CommandButton butFP 
      BackColor       =   &H00E0E0E0&
      Caption         =   "F/P"
      Height          =   495
      Left            =   360
      MaskColor       =   &H00FFFFFF&
      TabIndex        =   13
      TabStop         =   0   'False
      ToolTipText     =   "Compound Amount"
      Top             =   2880
      UseMaskColor    =   -1  'True
      Width           =   615
   End
   Begin VB.CommandButton butMinus 
      Caption         =   "-"
      Height          =   495
      Left            =   3480
      TabIndex        =   12
      TabStop         =   0   'False
      Top             =   4680
      Width           =   615
   End
   Begin VB.CommandButton butDot 
      Caption         =   "."
      Height          =   495
      Left            =   2760
      TabIndex        =   11
      TabStop         =   0   'False
      Top             =   4680
      Width           =   615
   End
   Begin VB.CommandButton but0 
      Caption         =   "0"
      Height          =   495
      Left            =   2040
      TabIndex        =   10
      TabStop         =   0   'False
      Top             =   4680
      Width           =   615
   End
   Begin VB.CommandButton but3 
      Caption         =   "3"
      Height          =   495
      Left            =   3480
      TabIndex        =   9
      TabStop         =   0   'False
      Top             =   4080
      Width           =   615
   End
   Begin VB.CommandButton but2 
      Caption         =   "2"
      Height          =   495
      Left            =   2760
      TabIndex        =   8
      TabStop         =   0   'False
      Top             =   4080
      Width           =   615
   End
   Begin VB.CommandButton but1 
      Caption         =   "1"
      Height          =   495
      Left            =   2040
      TabIndex        =   7
      TabStop         =   0   'False
      Top             =   4080
      Width           =   615
   End
   Begin VB.CommandButton but6 
      Caption         =   "6"
      Height          =   495
      Left            =   3480
      TabIndex        =   6
      TabStop         =   0   'False
      Top             =   3480
      Width           =   615
   End
   Begin VB.CommandButton but5 
      Caption         =   "5"
      Height          =   495
      Left            =   2760
      TabIndex        =   5
      TabStop         =   0   'False
      Top             =   3480
      Width           =   615
   End
   Begin VB.CommandButton but4 
      Caption         =   "4"
      Height          =   495
      Left            =   2040
      TabIndex        =   4
      TabStop         =   0   'False
      Top             =   3480
      Width           =   615
   End
   Begin VB.CommandButton but9 
      Caption         =   "9"
      Height          =   495
      Left            =   3480
      TabIndex        =   3
      TabStop         =   0   'False
      Top             =   2880
      Width           =   615
   End
   Begin VB.CommandButton but8 
      Caption         =   "8"
      Height          =   495
      Left            =   2760
      TabIndex        =   2
      TabStop         =   0   'False
      Top             =   2880
      Width           =   615
   End
   Begin VB.CommandButton but7 
      Caption         =   "7"
      Height          =   495
      Left            =   2040
      TabIndex        =   1
      TabStop         =   0   'False
      Top             =   2880
      Width           =   615
   End
   Begin VB.CommandButton butEnter 
      Caption         =   "Enter"
      Height          =   495
      Left            =   4200
      TabIndex        =   0
      TabStop         =   0   'False
      Top             =   4680
      Width           =   615
   End
End
Attribute VB_Name = "frmCIFCal"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Option Explicit

Dim strBuffer As String
Dim strFunction As String
Dim strInterest As String
Dim strPeriods As String

Dim iState As Integer

Public Sub miPrint()
    MsgBox "Calculator can not be printed", vbInformation
End Sub

Public Sub miSave()
    MsgBox "Calculator can not be saved", vbInformation

End Sub

Private Sub but1_Click()
    NumberPressed "1"
End Sub
Private Sub but2_Click()
    NumberPressed "2"
End Sub
Private Sub but3_Click()
    NumberPressed "3"
End Sub
Private Sub but4_Click()
    NumberPressed "4"
End Sub
Private Sub but5_Click()
    NumberPressed "5"
End Sub
Private Sub but6_Click()
    NumberPressed "6"
End Sub
Private Sub but7_Click()
    NumberPressed "7"
End Sub
Private Sub but8_Click()
    NumberPressed "8"
End Sub
Private Sub but9_Click()
    NumberPressed "9"
End Sub
Private Sub but0_Click()
    NumberPressed "0"
End Sub

Private Sub butBack_Click()
    If (iState = 1) Then
        If (Len(strInterest) > 0) Then
            strInterest = Mid(strInterest, 1, Len(strInterest) - 1)
        Else
            iState = 0
            strInterest = ""
            strPeriods = ""
        End If
    ElseIf (iState = 2) Then
        If (Len(strPeriods) > 0) Then
            strPeriods = Mid(strPeriods, 1, Len(strPeriods) - 1)
        Else
            iState = 1
            strPeriods = ""
        End If
    End If
    
    DisplayScreen
    
End Sub

Private Sub butClear_Click()
    iState = 0
    strInterest = ""
    strPeriods = ""
    
    DisplayScreen
    
End Sub

Private Sub butDot_Click()
    NumberPressed "."
End Sub
Private Sub butMinus_Click()
    NumberPressed "-"
End Sub


Private Sub NumberPressed(str As String)
    If (iState = 1) Then
        strInterest = strInterest & str
    ElseIf (iState = 2) Then
        strPeriods = strPeriods & str
    End If
    
    DisplayScreen
    
End Sub


Private Sub butEnter_Click()
    If (iState = 1) Then
        GetPeriods
    ElseIf (iState = 2) Then
        CalculateValue
        
        strFunction = ""
        strInterest = ""
        strPeriods = ""

        GetFunction
    End If
    
    DisplayScreen
End Sub

Private Sub butFP_Click()
    strFunction = "F/P"
    GetInterest
    DisplayScreen
End Sub
Private Sub butPF_Click()
    strFunction = "P/F"
    GetInterest
    DisplayScreen
End Sub
Private Sub butPA_Click()
    strFunction = "P/A"
    GetInterest
    DisplayScreen
End Sub
Private Sub butAP_Click()
    strFunction = "A/P"
    GetInterest
    DisplayScreen
End Sub
Private Sub butFA_Click()
    strFunction = "F/A"
    GetInterest
    DisplayScreen
End Sub
Private Sub butAF_Click()
    strFunction = "A/F"
    GetInterest
    DisplayScreen
End Sub
Private Sub butPG_Click()
    strFunction = "P/G"
    GetInterest
    DisplayScreen
End Sub
Private Sub butAG_Click()
    strFunction = "A/G"
    GetInterest
    DisplayScreen
End Sub



Private Sub DisplayScreen()
    txtScreen.Text = strBuffer
    
    If (iState >= 1) Then
        txtScreen.Text = txtScreen.Text & "(" & strFunction & ", " & strInterest
    End If
    
    If (iState >= 2) Then
        txtScreen.Text = txtScreen.Text & "%, " & strPeriods
    End If
    
    txtScreen.SelStart = Len(txtScreen.Text)
    txtScreen.SetFocus
    
End Sub

Private Sub GetFunction()
    txtMessage.Text = "Select Function"
    
    strFunction = ""
    strInterest = ""
    strPeriods = ""
    
    iState = 0

End Sub


Private Sub GetInterest()
    txtMessage.Text = "Enter Interest Amount and Press Enter"
    strInterest = ""
    strPeriods = ""
    
    iState = 1

End Sub
Private Sub GetPeriods()
    txtMessage.Text = "Enter Period Number and Press Enter"
    strPeriods = ""
    
    iState = 2
End Sub


Private Sub CalculateValue()
    strBuffer = strBuffer & "(" & strFunction & ", " & strInterest & "%, " & strPeriods & ")" & vbCrLf
    strBuffer = strBuffer & "          " & GetValue(strFunction, Val(strInterest), Val(strPeriods)) & vbCrLf

End Sub

Private Function GetValue(str As String, i1 As Currency, i2 As Long) As String
    Dim iResult As String
    
    Dim iInterest As Double
    Dim iYears As Double
    
    iInterest = i1
    iYears = i2

      iInterest = iInterest / 100#
      
      Select Case str:
        Case "F/P"
            iResult = Format(Constants.FGivenP(iInterest, iYears), "0.0000")
        Case "P/F"
            iResult = Format(Constants.PGivenF(iInterest, iYears), "0.0000")
        Case "A/F"
            iResult = Format(Constants.AGivenF(iInterest, iYears), "0.00000")
        Case "F/A"
            iResult = Format(Constants.FGivenA(iInterest, iYears), "0.0000")
        Case "A/P"
            iResult = Format(Constants.AGivenP(iInterest, iYears), "0.00000")
        Case "P/A"
            iResult = Format(Constants.PGivenA(iInterest, iYears), "0.0000")
        Case "P/G"
            iResult = Format(Constants.PGivenG(iInterest, iYears), "0.0000")
        Case "A/G"
            iResult = Format(Constants.AGivenG(iInterest, iYears), "0.0000")
        Case Else
            MsgBox "Function Not Found", vbInformation
      End Select
        
    GetValue = iResult
    
    
End Function


Private Sub Form_Load()

    strBuffer = ""
    
    GetFunction
    
End Sub

Private Sub txtScreen_KeyPress(KeyAscii As Integer)
    
    If (KeyAscii = 13) Then
        butEnter_Click
    ElseIf (KeyAscii = 8) Then
        butBack_Click
    ElseIf (KeyAscii = 27) Then
        butClear_Click

    Else
    
      Select Case Chr(KeyAscii):
        Case "0"
            but0_Click
        Case "1"
            but1_Click
        Case "2"
            but2_Click
        Case "3"
            but3_Click
        Case "4"
            but4_Click
        Case "5"
            but5_Click
        Case "6"
            but6_Click
        Case "7"
            but7_Click
        Case "8"
            but8_Click
        Case "9"
            but9_Click
      End Select
    End If
    
'    MsgBox "key: " & Chr(KeyAscii)
End Sub
