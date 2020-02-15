VERSION 5.00
Object = "{5E9E78A0-531B-11CF-91F6-C2863C385E30}#1.0#0"; "msflxgrd.ocx"
Begin VB.Form frmEvalAlternatives 
   Caption         =   "Form1"
   ClientHeight    =   6585
   ClientLeft      =   60
   ClientTop       =   345
   ClientWidth     =   10575
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   MDIChild        =   -1  'True
   ScaleHeight     =   6585
   ScaleWidth      =   10575
   Begin VB.CheckBox Check1 
      Caption         =   "Mutually Exclusive"
      Height          =   375
      Left            =   3240
      TabIndex        =   19
      Top             =   1680
      Width           =   2535
   End
   Begin VB.TextBox txtLife 
      Height          =   285
      Left            =   1200
      TabIndex        =   17
      Top             =   2160
      Width           =   1695
   End
   Begin VB.CommandButton butResults 
      Caption         =   "Results"
      Height          =   375
      Left            =   8880
      TabIndex        =   15
      Top             =   5400
      Width           =   1335
   End
   Begin VB.Frame Frame3 
      Height          =   2175
      Left            =   240
      TabIndex        =   10
      Top             =   2520
      Width           =   4095
      Begin VB.CommandButton butAdd 
         Caption         =   "Add"
         Height          =   375
         Left            =   240
         TabIndex        =   5
         Top             =   1680
         Width           =   1215
      End
      Begin VB.TextBox txtInitialCost 
         Enabled         =   0   'False
         Height          =   285
         Left            =   1080
         TabIndex        =   2
         Top             =   600
         Width           =   1215
      End
      Begin VB.TextBox txtCosts 
         Height          =   285
         Left            =   1680
         TabIndex        =   4
         Top             =   1320
         Width           =   1335
      End
      Begin VB.TextBox txtBenefits 
         Height          =   285
         Left            =   1680
         TabIndex        =   3
         Top             =   960
         Width           =   1335
      End
      Begin VB.TextBox txtName 
         Height          =   285
         Left            =   1080
         TabIndex        =   1
         Top             =   240
         Width           =   1455
      End
      Begin VB.Label Label2 
         Caption         =   "First Cost"
         Height          =   375
         Left            =   240
         TabIndex        =   14
         Top             =   600
         Width           =   735
      End
      Begin VB.Label Label4 
         Caption         =   "Annual Costs"
         Height          =   255
         Left            =   240
         TabIndex        =   13
         Top             =   1320
         Width           =   1935
      End
      Begin VB.Label Label5 
         Caption         =   "Annual Benefits"
         Height          =   255
         Left            =   240
         TabIndex        =   12
         Top             =   960
         Width           =   1335
      End
      Begin VB.Label Label6 
         Caption         =   "Name"
         Height          =   255
         Left            =   240
         TabIndex        =   11
         Top             =   240
         Width           =   735
      End
   End
   Begin MSFlexGridLib.MSFlexGrid theGrid 
      Height          =   1455
      Left            =   240
      TabIndex        =   9
      Top             =   4920
      Width           =   8295
      _ExtentX        =   14631
      _ExtentY        =   2566
      _Version        =   393216
      Cols            =   4
      FixedCols       =   0
   End
   Begin VB.TextBox txtMARR 
      Height          =   285
      Left            =   1080
      TabIndex        =   0
      Top             =   1800
      Width           =   615
   End
   Begin VB.ListBox lstAlternatives 
      Height          =   1035
      Left            =   240
      TabIndex        =   6
      Top             =   600
      Width           =   3855
   End
   Begin VB.Label Label8 
      Caption         =   "%"
      Height          =   255
      Left            =   1800
      TabIndex        =   18
      Top             =   1800
      Width           =   375
   End
   Begin VB.Label Label7 
      Caption         =   "Useful Life"
      Height          =   255
      Left            =   240
      TabIndex        =   16
      Top             =   2160
      Width           =   855
   End
   Begin VB.Label Label3 
      Caption         =   "MARR"
      Height          =   375
      Left            =   240
      TabIndex        =   8
      Top             =   1800
      Width           =   855
   End
   Begin VB.Label Label1 
      Caption         =   "Alternatives"
      Height          =   255
      Left            =   240
      TabIndex        =   7
      Top             =   240
      Width           =   1695
   End
End
Attribute VB_Name = "frmEvalAlternatives"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Option Explicit

Private Type AlternativeValues
    strName As String
    iAnnualCosts As Currency
    iAnnualBenefits As Currency

End Type

Dim iCount As Integer

Dim theAlternatives() As AlternativeValues

Private Sub butAdd_Click()
    ReDim Preserve theAlternatives(iCount + 1) As AlternativeValues
    
    theAlternatives(iCount).strName = txtName.Text
    
    '*** Calculate the benefits ***'
    If (txtBenefits.Text <> "") Then
        theAlternatives(iCount).iAnnualBenefits = txtBenefits.Text
    Else
        theAlternatives(iCount).iAnnualBenefits = 0
    End If
    
'    If (txtInitialCost.Text <> "") Then
'        theAlternatives(iCount).iAnnualBenefits = theAlternatives(iCount).iAnnualBenefits - (txtInitialCost.Text * Constants.AGivenP(txtMARR.Text / 100#, txtLife.Text))
'    End If
    
    '*** Calculate the costs ***'
    If (txtCosts.Text <> "") Then
        theAlternatives(iCount).iAnnualCosts = txtCosts.Text
    Else
        theAlternatives(iCount).iAnnualCosts = 0
    End If
    
    
    
    txtName.Text = ""
    txtInitialCost.Text = ""
    txtBenefits.Text = ""
    txtCosts.Text = ""
    
    iCount = iCount + 1
    LoadAlternativeList
    
    txtName.SetFocus
    
End Sub

Private Sub LoadAlternativeList()
    Dim i As Integer
    
    lstAlternatives.Clear
    
    i = 0
    While (i < iCount)
        lstAlternatives.AddItem theAlternatives(i).strName
        i = i + 1
    Wend
    
End Sub

Private Sub butResults_Click()
    resultBC
End Sub

Private Sub resultBC()
    Dim i As Integer
    Dim iBCRatio As Double
    
    Dim iBenefitDiff As Currency
    Dim iCostDiff As Currency
    Dim iBCRatioDiff As Currency
    
    Dim AltDefender As AlternativeValues
    Dim AltDefenderDefined As Boolean
    
    theGrid.Rows = 1
    
    
    AltDefenderDefined = False
    
    i = 0
    While (i < iCount)
        iBCRatio = theAlternatives(i).iAnnualBenefits / theAlternatives(i).iAnnualCosts
        theGrid.AddItem theAlternatives(i).strName & vbTab & theAlternatives(i).iAnnualBenefits & vbTab & theAlternatives(i).iAnnualCosts & vbTab & Format(iBCRatio, "0.00")
        
        
        If (AltDefenderDefined) Then
            iBenefitDiff = theAlternatives(i).iAnnualBenefits - AltDefender.iAnnualBenefits
            iCostDiff = theAlternatives(i).iAnnualCosts - AltDefender.iAnnualCosts
            iBCRatioDiff = iBenefitDiff / iCostDiff
            
            
            If (iBCRatioDiff > 1#) Then
            
                theGrid.AddItem theAlternatives(i).strName & " - " & AltDefender.strName & vbTab & iBenefitDiff & vbTab & iCostDiff & vbTab & Format(iBCRatioDiff, "0.00") & vbTab & "Keep " & theAlternatives(i).strName
                
                AltDefender.strName = theAlternatives(i).strName
                AltDefender.iAnnualBenefits = theAlternatives(i).iAnnualBenefits
                AltDefender.iAnnualCosts = theAlternatives(i).iAnnualCosts

            
            Else
                theGrid.AddItem theAlternatives(i).strName & " - " & AltDefender.strName & vbTab & iBenefitDiff & vbTab & iCostDiff & vbTab & Format(iBCRatioDiff, "0.00") & vbTab & "Keep " & AltDefender.strName
            
            End If
            
            
        Else
            AltDefender.strName = theAlternatives(i).strName
            AltDefender.iAnnualBenefits = theAlternatives(i).iAnnualBenefits
            AltDefender.iAnnualCosts = theAlternatives(i).iAnnualCosts

            AltDefenderDefined = True
        
        End If
        
        i = i + 1
    Wend


End Sub

Private Sub Form_Load()
    iCount = 0
    ReDim theAlternatives(0) As AlternativeValues

    theGrid.FormatString = "                                        |Benefits            |Costs               |B/C Ratio          |Result                        "
End Sub

Private Sub rdoBC_Click()
    EnableAll
    txtMARR.Enabled = False
    txtInitialCost.Enabled = False
End Sub

Private Sub rdoIRR_Click()
    EnableAll
End Sub

Private Sub EnableAll()
    txtMARR.Enabled = True
    txtInitialCost.Enabled = True
End Sub
