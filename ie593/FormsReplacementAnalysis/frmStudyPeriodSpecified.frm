VERSION 5.00
Begin VB.Form frmStudyPeriodSpecified 
   Caption         =   "Study Period Specified"
   ClientHeight    =   6060
   ClientLeft      =   60
   ClientTop       =   750
   ClientWidth     =   9465
   LinkTopic       =   "Form1"
   ScaleHeight     =   6060
   ScaleWidth      =   9465
   StartUpPosition =   2  'CenterScreen
   Begin VB.CommandButton Command1 
      Caption         =   "Close"
      Height          =   375
      Left            =   7680
      TabIndex        =   27
      Top             =   5520
      Width           =   1215
   End
   Begin VB.Frame Frame2 
      Caption         =   "Defender"
      Height          =   4095
      Left            =   4440
      TabIndex        =   4
      Top             =   1080
      Width           =   4455
      Begin VB.CommandButton calcD 
         Caption         =   "Calculate"
         Height          =   375
         Left            =   1440
         TabIndex        =   26
         Top             =   3360
         Width           =   1575
      End
      Begin VB.TextBox tbAWd 
         Height          =   375
         Left            =   1200
         TabIndex        =   24
         Top             =   2640
         Width           =   1695
      End
      Begin VB.TextBox tbSVd 
         Height          =   375
         Left            =   2040
         TabIndex        =   23
         Top             =   1800
         Width           =   1695
      End
      Begin VB.TextBox tbNd 
         Height          =   375
         Left            =   240
         TabIndex        =   22
         Top             =   1800
         Width           =   1695
      End
      Begin VB.TextBox tbAOCd 
         Height          =   375
         Left            =   2040
         TabIndex        =   21
         Top             =   600
         Width           =   1695
      End
      Begin VB.TextBox tbMV 
         Height          =   375
         Left            =   240
         TabIndex        =   20
         Top             =   600
         Width           =   1695
      End
      Begin VB.Label Label12 
         Caption         =   "Annual Worth"
         Height          =   495
         Left            =   1320
         TabIndex        =   19
         Top             =   2400
         Width           =   1575
      End
      Begin VB.Label Label11 
         Caption         =   "Salvage Value:"
         Height          =   375
         Left            =   2040
         TabIndex        =   18
         Top             =   1560
         Width           =   1455
      End
      Begin VB.Label Label10 
         Caption         =   "Number of Years:"
         Height          =   375
         Left            =   240
         TabIndex        =   17
         Top             =   1560
         Width           =   1575
      End
      Begin VB.Label Label9 
         Caption         =   "Annual Operating Costs:"
         Height          =   375
         Left            =   2040
         TabIndex        =   16
         Top             =   360
         Width           =   1935
      End
      Begin VB.Label Label8 
         Caption         =   "Present Value:"
         Height          =   375
         Left            =   240
         TabIndex        =   15
         Top             =   360
         Width           =   1335
      End
   End
   Begin VB.Frame Frame1 
      Caption         =   "Challenger"
      Height          =   4095
      Left            =   240
      TabIndex        =   3
      Top             =   1080
      Width           =   3975
      Begin VB.CommandButton calcC 
         Caption         =   "Calculate"
         Height          =   375
         Left            =   1080
         TabIndex        =   25
         Top             =   3360
         Width           =   1575
      End
      Begin VB.TextBox tbAWc 
         Height          =   375
         Left            =   1080
         TabIndex        =   14
         Top             =   2640
         Width           =   1695
      End
      Begin VB.TextBox tbSVc 
         Height          =   375
         Left            =   2040
         TabIndex        =   12
         Top             =   1800
         Width           =   1695
      End
      Begin VB.TextBox tbNc 
         Height          =   375
         Left            =   240
         TabIndex        =   11
         Top             =   1800
         Width           =   1695
      End
      Begin VB.TextBox tbAOCc 
         Height          =   375
         Left            =   2040
         TabIndex        =   8
         Top             =   600
         Width           =   1695
      End
      Begin VB.TextBox tbP 
         Height          =   375
         Left            =   240
         TabIndex        =   7
         Top             =   600
         Width           =   1695
      End
      Begin VB.Label Label7 
         Caption         =   "Annual Worth:"
         Height          =   375
         Left            =   1320
         TabIndex        =   13
         Top             =   2400
         Width           =   1455
      End
      Begin VB.Label Label6 
         Caption         =   "Salvage Value:"
         Height          =   375
         Left            =   2040
         TabIndex        =   10
         Top             =   1560
         Width           =   1695
      End
      Begin VB.Label Label5 
         Caption         =   "Number of Years:"
         Height          =   375
         Left            =   240
         TabIndex        =   9
         Top             =   1560
         Width           =   1335
      End
      Begin VB.Label Label4 
         Caption         =   "Annual Operating Costs:"
         Height          =   255
         Left            =   2040
         TabIndex        =   6
         Top             =   360
         Width           =   1815
      End
      Begin VB.Label Label3 
         Caption         =   "Present Value:"
         Height          =   255
         Left            =   240
         TabIndex        =   5
         Top             =   360
         Width           =   1335
      End
   End
   Begin VB.TextBox tbPercentage 
      Height          =   375
      Left            =   3600
      TabIndex        =   1
      Top             =   240
      Width           =   855
   End
   Begin VB.Label Label2 
      Caption         =   "%"
      Height          =   375
      Left            =   4680
      TabIndex        =   2
      Top             =   240
      Width           =   495
   End
   Begin VB.Label Label1 
      Caption         =   "Interest Rate = "
      Height          =   375
      Left            =   2280
      TabIndex        =   0
      Top             =   240
      Width           =   1215
   End
   Begin VB.Menu file 
      Caption         =   "File"
      Begin VB.Menu close 
         Caption         =   "Close"
      End
   End
End
Attribute VB_Name = "frmStudyPeriodSpecified"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
    
'    Private Sub calcC_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)
    Private Sub calcC_Click()
        Dim P As Double
        Dim Nc As Double
        Dim AOCc As Double
        Dim SVc As Double
        Dim i As Double
        Dim AWc As Double
        Dim resC As Double
        
        If (Not IsNumeric(tbP.Text)) Then
            MsgBox "Please Enter Present Value", vbInformation
        ElseIf (Not IsNumeric(tbAOCc.Text)) Then
            MsgBox "Please Enter Challenger's Annual Costs", vbInformation
        ElseIf (Not IsNumeric(tbNc.Text)) Then
            MsgBox "Please Enter Challenger's Number of Years", vbInformation
        ElseIf (Not IsNumeric(tbSVc.Text)) Then
            MsgBox "Please Enter Challenger's Salvage Value", vbInformation
        ElseIf (Not IsNumeric(tbPercentage.Text)) Then
            MsgBox "Please Enter Precent Value", vbInformation
        Else
            P = CDec(tbP.Text)
            SVc = CDec(tbSVc.Text)
            Nc = CDec(tbNc.Text)
            AOCc = CDec(tbAOCc.Text)
            i = Val(tbPercentage.Text) / 100#

            resC = ((-1) * P) * ((i * (1 + i) ^ Nc) / ((1 + i) ^ Nc - 1)) - AOCc + SVc * (i / ((1 + i) ^ Nc - 1))
            tbAWc.Text = Format(resC, "0.00")
        End If
    End Sub

'    Private Sub calcD_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)
    Private Sub calcD_Click()
        Dim MV As Double
        Dim Nd As Double
        Dim Nc As Double
        Dim AOCd As Double
        Dim SVd As Double
        Dim i As Double
        Dim AWd As Double
        Dim resD As Double
        
        If (Not IsNumeric(tbMV.Text)) Then
            MsgBox "Please Enter Market Value", vbInformation
        ElseIf (Not IsNumeric(tbAOCd.Text)) Then
            MsgBox "Please Enter Deffender's Annual Costs", vbInformation
        ElseIf (Not IsNumeric(tbNd.Text)) Then
            MsgBox "Please Enter Deffender's Number of Years", vbInformation
        ElseIf (Not IsNumeric(tbSVd.Text)) Then
            MsgBox "Please Enter Deffender's Salvage Value", vbInformation
        ElseIf (Not IsNumeric(tbPercentage.Text)) Then
            MsgBox "Please Enter Precent Value", vbInformation
        ElseIf (Not IsNumeric(tbNc.Text)) Then
            MsgBox "Please Enter Challenger's Number of Years", vbInformation
        Else
        MV = CDec(tbMV.Text)
        SVd = CDec(tbSVd.Text)
        Nd = CDec(tbNd.Text)
        Nc = CDec(tbNc.Text)
        AOCd = CDec(tbAOCd.Text)
        i = Val(tbPercentage.Text) / 100#

        resD = ((-1 * MV) - AOCd * (((1 + i) ^ Nd - 1) / (i * (1 + i) ^ Nd))) * ((i * (1 + i) ^ Nc) / ((1 + i) ^ Nc - 1) + SVd * (i / ((1 + i) ^ Nc - 1)))
        tbAWd.Text = Format(resD, "0.00")
        
        
        End If
    End Sub

Private Sub close_Click()
    Unload Me
End Sub

Private Sub Command1_Click()
    Unload Me
End Sub
