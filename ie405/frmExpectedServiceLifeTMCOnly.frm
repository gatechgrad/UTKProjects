VERSION 5.00
Begin VB.Form frmExpectedServiceLifeTMCOnly 
   BackColor       =   &H00E0E0E0&
   Caption         =   "Expected Service Life"
   ClientHeight    =   7395
   ClientLeft      =   60
   ClientTop       =   450
   ClientWidth     =   7395
   LinkTopic       =   "Form1"
   ScaleHeight     =   7395
   ScaleWidth      =   7395
   StartUpPosition =   2  'CenterScreen
   Begin VB.CommandButton butCancel 
      Caption         =   "Cancel"
      Height          =   375
      Left            =   1560
      TabIndex        =   9
      Top             =   6840
      Width           =   1095
   End
   Begin VB.CommandButton butOkay 
      Caption         =   "OK"
      Height          =   375
      Left            =   240
      TabIndex        =   8
      Top             =   6840
      Width           =   1095
   End
   Begin VB.ListBox listData 
      BackColor       =   &H00FFFFFF&
      BeginProperty Font 
         Name            =   "Courier New"
         Size            =   8.25
         Charset         =   0
         Weight          =   400
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      ForeColor       =   &H00000000&
      Height          =   1740
      Left            =   240
      TabIndex        =   11
      TabStop         =   0   'False
      Top             =   4080
      Width           =   3975
   End
   Begin VB.TextBox txtCostOfCapital 
      BackColor       =   &H00FFFFFF&
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   3000
      Locked          =   -1  'True
      TabIndex        =   0
      Top             =   120
      Width           =   735
   End
   Begin VB.Frame Frame1 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Add Year Values"
      Height          =   2655
      Left            =   240
      TabIndex        =   6
      Top             =   720
      Width           =   6255
      Begin VB.TextBox txtTMC 
         BackColor       =   &H00FFFFFF&
         ForeColor       =   &H00000000&
         Height          =   375
         Left            =   2880
         TabIndex        =   2
         Top             =   840
         Width           =   855
      End
      Begin VB.CommandButton butAdd 
         Caption         =   "Add"
         Height          =   375
         Left            =   120
         TabIndex        =   3
         Top             =   1920
         Width           =   1215
      End
      Begin VB.TextBox txtEOY 
         BackColor       =   &H00E0E0E0&
         ForeColor       =   &H00000000&
         Height          =   375
         Left            =   2880
         Locked          =   -1  'True
         TabIndex        =   1
         Top             =   360
         Width           =   855
      End
      Begin VB.Label Label2 
         BackColor       =   &H00E0E0E0&
         Caption         =   "Total Marginal Costs (TMC):"
         Height          =   375
         Left            =   240
         TabIndex        =   20
         Top             =   840
         Width           =   2295
      End
      Begin VB.Label Label1 
         BackColor       =   &H00E0E0E0&
         Caption         =   "End of Year (EOY): "
         Height          =   255
         Left            =   240
         TabIndex        =   10
         Top             =   360
         Width           =   1815
      End
   End
   Begin VB.CommandButton butClearAll 
      Caption         =   "Clear All"
      Height          =   375
      Left            =   240
      TabIndex        =   4
      Top             =   6000
      Width           =   1335
   End
   Begin VB.TextBox txtESL 
      BackColor       =   &H00E0E0E0&
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   5280
      TabIndex        =   5
      Top             =   6000
      Width           =   735
   End
   Begin VB.TextBox txtMinEUAC 
      BackColor       =   &H00E0E0E0&
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   4440
      TabIndex        =   7
      Top             =   6480
      Width           =   1575
   End
   Begin VB.Label Label4 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Cost of Capital:"
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   360
      TabIndex        =   19
      Top             =   120
      Width           =   1815
   End
   Begin VB.Label Label5 
      BackColor       =   &H00E0E0E0&
      Caption         =   "%"
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   3840
      TabIndex        =   18
      Top             =   120
      Width           =   375
   End
   Begin VB.Label Label6 
      BackColor       =   &H00E0E0E0&
      Caption         =   "End of Year (EOY)"
      ForeColor       =   &H00000000&
      Height          =   495
      Left            =   240
      TabIndex        =   17
      Top             =   3600
      Width           =   855
   End
   Begin VB.Label Label11 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Total Marginal Cost (TMC)"
      ForeColor       =   &H00000000&
      Height          =   615
      Left            =   1560
      TabIndex        =   16
      Top             =   3480
      Width           =   855
   End
   Begin VB.Label Label12 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Equivalent Uniform Costs (EUAC)"
      ForeColor       =   &H00000000&
      Height          =   615
      Left            =   2640
      TabIndex        =   15
      Top             =   3480
      Width           =   975
   End
   Begin VB.Label Label13 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Economic Service Life (ESL):"
      ForeColor       =   &H00000000&
      Height          =   255
      Left            =   3000
      TabIndex        =   14
      Top             =   6000
      Width           =   2175
   End
   Begin VB.Label Label14 
      BackColor       =   &H00E0E0E0&
      Caption         =   "years"
      ForeColor       =   &H00000000&
      Height          =   255
      Left            =   6120
      TabIndex        =   13
      Top             =   6000
      Width           =   855
   End
   Begin VB.Label Label15 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Minimum EUAC:"
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   3000
      TabIndex        =   12
      Top             =   6480
      Width           =   1335
   End
End
Attribute VB_Name = "frmExpectedServiceLifeTMCOnly"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Private Type RAGridRow
    iEOY As Currency
    iTMC As Currency
    iEUAC As Currency
End Type

Dim RAGrid(1024) As RAGridRow
Dim iEOYCount As Integer
Dim iCostOfCapital As Double

Private Sub butAdd_Click()
    If (Not IsNumeric(txtCostOfCapital.Text)) Then
        MsgBox "Please Enter Cost of Capital", vbInformation
    Else
    
        txtCostOfCapital.Locked = True
        txtCostOfCapital.BackColor = RGB(192, 192, 192)
    
        iCostOfCapital = Val(txtCostOfCapital.Text) / 100#
    
        RAGrid(iEOYCount).iEOY = txtEOY.Text
        
        If (txtTMC.Text <> "") Then
            RAGrid(iEOYCount).iTMC = txtTMC.Text
        Else
            RAGrid(iEOYCount).iTMC = 0
        End If

    
        If (iEOYCount > 1) Then
            RAGrid(iEOYCount).iEUAC = GetEUAC(iEOYCount)
        Else
            RAGrid(iEOYCount).iEUAC = RAGrid(iEOYCount).iTMC
        End If
        
        iEOYCount = iEOYCount + 1
        CalculateGrid

        txtEOY.Text = iEOYCount
        txtTMC.Text = ""
    
        txtTMC.SetFocus
    End If

End Sub

Private Sub CalculateGrid()
    Dim i As Integer
    
    listData.Clear
    
    i = 0
    While (i < iEOYCount)
        listData.AddItem lpad(RAGrid(i).iEOY, 2) & _
                         lpad(RAGrid(i).iTMC, 12) & _
                         lpad(RAGrid(i).iEUAC, 12)
        i = i + 1
    Wend
                     
    FindESL
                     
    
End Sub

Private Function lpad(str As Variant, iSize As Integer)
    Dim i As Integer
    Dim strReturn As String
    
    strReturn = ""
    
    i = iSize - Len(str)
    While (i > 0)
        strReturn = strReturn & " "
        i = i - 1
    
    Wend
    
    strReturn = strReturn & str

    lpad = strReturn
End Function

Private Sub butCancel_Click()
    Unload Me
End Sub

Private Sub butClearAll_Click()
    listData.Clear
    iEOYCount = 1
    txtEOY.Text = iEOYCount
    txtTMC.Text = ""
    txtCostOfCapital.Text = ""
    txtCostOfCapital.Locked = False
    txtCostOfCapital.BackColor = RGB(255, 255, 255)


End Sub



Private Sub butOkay_Click()
    
  If (txtMinEUAC.Text <> "") Then
    If (MainForm.rdoDef.Value = True) Then
        MainForm.txtDefESL.Text = txtESL.Text
        MainForm.txtDefMinEUAC.Text = txtMinEUAC.Text
    
    ElseIf (MainForm.rdoChal.Value = True) Then
        MainForm.txtChalESL.Text = txtESL.Text
        MainForm.txtChalMinEUAC.Text = txtMinEUAC.Text
    End If
    
    Unload Me
  Else
        MsgBox "No EUAC Calculated", vbInformation
  End If

End Sub

Private Sub Form_Load()
    iEOYCount = 1
    txtEOY.Text = iEOYCount
    txtCostOfCapital.Locked = False
    txtCostOfCapital.BackColor = RGB(255, 255, 255)

    
End Sub

Private Function GetEUAC(iYear As Integer) As Currency
    Dim i As Integer
    Dim iReturn As Currency
    
    i = 1
    iReturn = 0
    
    While (i <= iYear)
        
       iReturn = iReturn + (RAGrid(i).iTMC * Constants.PGivenF(iCostOfCapital, i * 1#))
       i = i + 1
    Wend
    
    iReturn = iReturn * Constants.AGivenP(iCostOfCapital, iYear * 1#)
    
    GetEUAC = Round(iReturn, 0)

End Function

Private Sub FindESL()
    Dim iValue As Currency
    Dim iValueYear As Integer
    Dim i As Integer
    Dim isGreater As Boolean
    
    If (iEOYCount - 1 >= 1) Then
        i = 1
        iValue = RAGrid(i).iEUAC
        iValueYear = i
        i = i + 1
        isGreater = False
        
        While ((i < iEOYCount) And (Not isGreater))
            If (RAGrid(i).iEUAC < iValue) Then
                iValue = RAGrid(i).iEUAC
                iValueYear = i
            Else
                isGreater = True
            End If
            
            i = i + 1
        Wend
    End If
    
    txtESL.Text = iValueYear
    txtMinEUAC.Text = iValue


End Sub



