VERSION 5.00
Object = "{5E9E78A0-531B-11CF-91F6-C2863C385E30}#1.0#0"; "msflxgrd.ocx"
Begin VB.Form frmEval 
   Caption         =   "Evaluation of Alternatives"
   ClientHeight    =   7335
   ClientLeft      =   60
   ClientTop       =   345
   ClientWidth     =   8460
   LinkTopic       =   "Form1"
   MDIChild        =   -1  'True
   ScaleHeight     =   7335
   ScaleWidth      =   8460
   Begin MSFlexGridLib.MSFlexGrid gridAnalysis 
      Height          =   735
      Left            =   360
      TabIndex        =   21
      Top             =   5520
      Width           =   3015
      _ExtentX        =   5318
      _ExtentY        =   1296
      _Version        =   393216
      FixedRows       =   0
      FixedCols       =   0
   End
   Begin MSFlexGridLib.MSFlexGrid gridEval 
      Height          =   1815
      Left            =   3960
      TabIndex        =   20
      Top             =   4200
      Width           =   3495
      _ExtentX        =   6165
      _ExtentY        =   3201
      _Version        =   393216
      Cols            =   1
      FixedCols       =   0
   End
   Begin VB.CommandButton butEval 
      Caption         =   "Evaluate"
      Height          =   375
      Left            =   5880
      TabIndex        =   19
      Top             =   6240
      Width           =   1335
   End
   Begin VB.CommandButton butClear 
      Caption         =   "Clear"
      Height          =   375
      Left            =   360
      TabIndex        =   18
      Top             =   6360
      Width           =   1215
   End
   Begin VB.ListBox List1 
      Height          =   645
      Left            =   1680
      TabIndex        =   12
      Top             =   240
      Width           =   2175
   End
   Begin VB.Frame Frame1 
      Height          =   2535
      Left            =   240
      TabIndex        =   6
      Top             =   1440
      Width           =   5295
      Begin VB.TextBox txtName 
         Height          =   285
         Left            =   1920
         TabIndex        =   16
         Top             =   240
         Width           =   2055
      End
      Begin VB.CommandButton butAdd 
         Caption         =   "Add"
         Height          =   375
         Left            =   3960
         TabIndex        =   14
         Top             =   1800
         Width           =   1215
      End
      Begin VB.TextBox txtLife 
         Height          =   285
         Left            =   1320
         TabIndex        =   4
         Top             =   1920
         Width           =   1695
      End
      Begin VB.TextBox txtSalvage 
         Height          =   285
         Left            =   1560
         TabIndex        =   3
         Top             =   1560
         Width           =   1455
      End
      Begin VB.TextBox txtAnnual 
         Height          =   285
         Left            =   2040
         TabIndex        =   2
         Top             =   1200
         Width           =   1575
      End
      Begin VB.TextBox txtInitial 
         Height          =   285
         Left            =   1440
         TabIndex        =   1
         Top             =   840
         Width           =   1575
      End
      Begin VB.Label Label2 
         Caption         =   "Alternative Name:"
         Height          =   255
         Left            =   240
         TabIndex        =   17
         Top             =   240
         Width           =   1335
      End
      Begin VB.Label Label8 
         Caption         =   "Life:"
         Height          =   255
         Left            =   240
         TabIndex        =   13
         Top             =   1920
         Width           =   1095
      End
      Begin VB.Label Label6 
         Caption         =   "Salvage Value:"
         Height          =   255
         Left            =   240
         TabIndex        =   10
         Top             =   1560
         Width           =   1095
      End
      Begin VB.Label Label5 
         Caption         =   "Annual Cash Flow:"
         Height          =   255
         Left            =   240
         TabIndex        =   9
         Top             =   1200
         Width           =   1455
      End
      Begin VB.Label Label4 
         Caption         =   "Initial Cost:"
         Height          =   255
         Left            =   240
         TabIndex        =   8
         Top             =   840
         Width           =   1215
      End
   End
   Begin VB.TextBox Text1 
      Height          =   285
      Left            =   5160
      TabIndex        =   0
      Top             =   240
      Width           =   1815
   End
   Begin MSFlexGridLib.MSFlexGrid theGrid 
      Height          =   1335
      Left            =   360
      TabIndex        =   15
      Top             =   4200
      Width           =   3015
      _ExtentX        =   5318
      _ExtentY        =   2355
      _Version        =   393216
      FixedCols       =   0
   End
   Begin VB.Label Label7 
      Caption         =   "Alternatives:"
      Height          =   255
      Left            =   120
      TabIndex        =   11
      Top             =   240
      Width           =   1455
   End
   Begin VB.Label Label3 
      Caption         =   "%"
      Height          =   255
      Left            =   7080
      TabIndex        =   7
      Top             =   240
      Width           =   375
   End
   Begin VB.Label Label1 
      Caption         =   "MARR:"
      Height          =   255
      Left            =   4080
      TabIndex        =   5
      Top             =   240
      Width           =   975
   End
End
Attribute VB_Name = "frmEval"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Option Explicit

Private Type AlternativeValues
    strName As String
    iCashFlow() As Currency
    iCashFlowCount As Integer
End Type

Dim iCount As Integer

Dim theAlternatives() As AlternativeValues

Private Sub butAdd_Click()
    Dim i As Integer
    
    Dim theArray() As Double
    
    iCount = iCount + 1
    
    ReDim Preserve theAlternatives(iCount)
    theAlternatives(iCount).strName = txtName.Text
    
    theAlternatives(iCount).iCashFlowCount = txtLife.Text
    theAlternatives(iCount).iCashFlowCount = theAlternatives(iCount).iCashFlowCount + 1
    ReDim Preserve theAlternatives(iCount).iCashFlow(theAlternatives(iCount).iCashFlowCount)
    
    ReDim theArray(theAlternatives(iCount).iCashFlowCount)
    
    i = 0
    While (i < theAlternatives(iCount).iCashFlowCount)
        If (i = 0) Then
            theAlternatives(iCount).iCashFlow(i) = txtInitial.Text
            
        Else
            If (i = theAlternatives(iCount).iCashFlowCount - 1) Then
                theAlternatives(iCount).iCashFlow(i) = txtSalvage.Text
            End If
            theAlternatives(iCount).iCashFlow(i) = theAlternatives(iCount).iCashFlow(i) + txtAnnual.Text
        End If
        
        theArray(i) = theAlternatives(iCount).iCashFlow(i)
        
        
        i = i + 1
    Wend
    
    
    
    MsgBox IRR(theArray)
    Update_Grid
    
End Sub

Private Sub Update_Grid()
    Dim i As Integer
    Dim j As Integer
    Dim strItem As String
    
    theGrid.Rows = 1
    theGrid.Cols = iCount
    
    
    strItem = ""
    j = 1
    While (j <= iCount)
        If (j > 1) Then
            strItem = strItem & "|"
        End If
    
    
        strItem = strItem & theAlternatives(j).strName & "             "
        j = j + 1
    Wend
    theGrid.FormatString = strItem
    
    i = 0
    While (i < 100)
        strItem = ""
        
        j = 1
        While (j <= iCount)
            
            If (j > 1) Then
                strItem = strItem & vbTab
            End If
            
            If (theAlternatives(j).iCashFlowCount > i) Then
                strItem = strItem & theAlternatives(j).iCashFlow(i)
            Else
                strItem = strItem & ""
            End If
            
            j = j + 1
        Wend
    
        theGrid.AddItem strItem
    
        i = i + 1
    Wend

End Sub

Private Sub butClear_Click()
    iCount = 0
    ReDim Preserve theAlternatives(iCount)
    Update_Grid
    
End Sub

Private Function Get_LCM() As Long
    Get_LCM = 12

End Function

Private Sub Form_Load()
    iCount = 0
End Sub
