VERSION 5.00
Begin VB.Form MainForm 
   BackColor       =   &H00E0E0E0&
   Caption         =   "Replacement Analysis"
   ClientHeight    =   5760
   ClientLeft      =   165
   ClientTop       =   555
   ClientWidth     =   7875
   LinkTopic       =   "Form1"
   ScaleHeight     =   5760
   ScaleWidth      =   7875
   StartUpPosition =   2  'CenterScreen
   Begin VB.CommandButton Command2 
      Caption         =   "Close"
      Height          =   375
      Left            =   6240
      TabIndex        =   14
      Top             =   4920
      Width           =   975
   End
   Begin VB.CommandButton butClear 
      Caption         =   "Clear All"
      Height          =   375
      Left            =   3720
      TabIndex        =   13
      Top             =   4920
      Width           =   1095
   End
   Begin VB.OptionButton rdoChal 
      BackColor       =   &H00E0E0E0&
      Height          =   375
      Left            =   240
      TabIndex        =   4
      Top             =   2520
      Width           =   375
   End
   Begin VB.OptionButton rdoDef 
      BackColor       =   &H00E0E0E0&
      Height          =   375
      Left            =   240
      TabIndex        =   3
      Top             =   360
      Value           =   -1  'True
      Width           =   375
   End
   Begin VB.CommandButton Command1 
      Caption         =   "Results"
      Height          =   375
      Left            =   5040
      TabIndex        =   2
      Top             =   4920
      Width           =   975
   End
   Begin VB.Frame Frame2 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Challenger"
      ForeColor       =   &H00000000&
      Height          =   2175
      Left            =   720
      TabIndex        =   1
      Top             =   2520
      Width           =   6495
      Begin VB.TextBox txtChalMinEUAC 
         BackColor       =   &H00E0E0E0&
         ForeColor       =   &H00000000&
         Height          =   285
         Left            =   2520
         Locked          =   -1  'True
         TabIndex        =   12
         Top             =   840
         Width           =   1455
      End
      Begin VB.TextBox txtChalESL 
         BackColor       =   &H00E0E0E0&
         ForeColor       =   &H00000000&
         Height          =   285
         Left            =   2520
         Locked          =   -1  'True
         TabIndex        =   11
         Top             =   360
         Width           =   1455
      End
      Begin VB.Label Label4 
         BackColor       =   &H00E0E0E0&
         Caption         =   "Minimum EUAC:"
         ForeColor       =   &H00000000&
         Height          =   375
         Left            =   360
         TabIndex        =   10
         Top             =   840
         Width           =   1935
      End
      Begin VB.Label Label3 
         BackColor       =   &H00E0E0E0&
         Caption         =   "Economic Service Life:"
         ForeColor       =   &H00000000&
         Height          =   375
         Left            =   360
         TabIndex        =   9
         Top             =   360
         Width           =   1935
      End
   End
   Begin VB.Frame Frame1 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Defender"
      ForeColor       =   &H00000000&
      Height          =   1935
      Left            =   720
      TabIndex        =   0
      Top             =   360
      Width           =   6495
      Begin VB.TextBox txtDefMinEUAC 
         BackColor       =   &H00E0E0E0&
         ForeColor       =   &H00000000&
         Height          =   285
         Left            =   2040
         Locked          =   -1  'True
         TabIndex        =   8
         Top             =   720
         Width           =   1335
      End
      Begin VB.TextBox txtDefESL 
         BackColor       =   &H00E0E0E0&
         ForeColor       =   &H00000000&
         Height          =   285
         Left            =   2040
         Locked          =   -1  'True
         TabIndex        =   6
         Top             =   360
         Width           =   1335
      End
      Begin VB.Label Label2 
         BackColor       =   &H00E0E0E0&
         Caption         =   "Minimum EUAC:"
         ForeColor       =   &H00000000&
         Height          =   375
         Left            =   240
         TabIndex        =   7
         Top             =   720
         Width           =   1575
      End
      Begin VB.Label Label1 
         BackColor       =   &H00E0E0E0&
         Caption         =   "Economic Service Life:"
         ForeColor       =   &H00000000&
         Height          =   255
         Left            =   240
         TabIndex        =   5
         Top             =   360
         Width           =   1935
      End
   End
   Begin VB.Menu file 
      Caption         =   "&File"
      Begin VB.Menu exit 
         Caption         =   "C&lose"
      End
   End
   Begin VB.Menu ra 
      Caption         =   "&Replacement Analysis"
      Begin VB.Menu esl 
         Caption         =   "&Expected Service Life (MV and AE Known)"
      End
      Begin VB.Menu esltmc 
         Caption         =   "Expected Service Life (&TMC Known)"
      End
      Begin VB.Menu icaesv 
         Caption         =   "&Inital Cost / Annual Expenses / Salvage Value"
      End
   End
End
Attribute VB_Name = "MainForm"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Private Sub about_Click()
    frmAbout.Show vbModal, Me
End Sub

Private Sub butClear_Click()
    txtDefESL.Text = ""
    txtDefMinEUAC.Text = ""
    
    txtChalESL.Text = ""
    txtChalMinEUAC.Text = ""
    
    
End Sub

Private Sub cal_Click()
    frmCIFCal.Show vbModal, Me
    
End Sub

Private Sub Command1_Click()
    Dim iDefEUAC As Double
    Dim iChalEUAC As Double
    
    If (txtDefMinEUAC.Text = "") Then
        MsgBox "Please Select EUAC for Defender"
    ElseIf (txtChalMinEUAC.Text = "") Then
        MsgBox "Please Select EUAC for Challenger"
    Else
    
        iDefEUAC = txtDefMinEUAC.Text
        iChalEUAC = txtChalMinEUAC.Text
    
        If (iDefEUAC > iChalEUAC) Then
            MsgBox "Replace Defender with Challenger", vbInformation, "Results"
        ElseIf (iChalEUAC > iDefEUAC) Then
            MsgBox "Keep Defender", vbInformation, Results
        ElseIf (iChalEUAC = iDefEUAC) Then
            MsgBox "Defender Minimum EUAC equals Challenger EUAC.  Replace or Keep Defender"
        End If
    End If
    
'    frmResults.Show vbModal, Me
End Sub

Private Sub Command2_Click()
    Unload Me
End Sub

Private Sub esl_Click()
    frmExpectedServiceLife.Show vbModal, Me
End Sub

Private Sub esltmc_Click()
    frmExpectedServiceLifeTMCOnly.Show vbModal, Me
End Sub

Private Sub exit_Click()
    Unload Me
End Sub

Private Sub icaesv_Click()
    frmICAESV.Show vbModal, Me
End Sub

Private Sub rsad_Click()
    frmReplacementStudyApproaches.Show vbModal, Me
End Sub
