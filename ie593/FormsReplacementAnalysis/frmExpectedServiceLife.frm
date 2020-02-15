VERSION 5.00
Object = "{F9043C88-F6F2-101A-A3C9-08002B2F49FB}#1.2#0"; "comdlg32.ocx"
Object = "{5E9E78A0-531B-11CF-91F6-C2863C385E30}#1.0#0"; "msflxgrd.ocx"
Begin VB.Form frmReplacement 
   BackColor       =   &H00E0E0E0&
   Caption         =   "Expected Service Life"
   ClientHeight    =   6570
   ClientLeft      =   60
   ClientTop       =   450
   ClientWidth     =   11040
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   MDIChild        =   -1  'True
   ScaleHeight     =   6570
   ScaleWidth      =   11040
   Begin VB.CommandButton butRemoveLast 
      Caption         =   "Remove Last"
      Height          =   375
      Left            =   240
      TabIndex        =   37
      Top             =   5040
      Width           =   1455
   End
   Begin VB.CommandButton butCostOfCapital 
      Caption         =   "Select"
      Height          =   255
      Left            =   2880
      TabIndex        =   36
      Top             =   720
      Width           =   855
   End
   Begin MSComDlg.CommonDialog CommonDialogPrinter 
      Left            =   8160
      Top             =   5400
      _ExtentX        =   847
      _ExtentY        =   847
      _Version        =   393216
   End
   Begin MSComDlg.CommonDialog CommonDialog1 
      Left            =   7560
      Top             =   5400
      _ExtentX        =   847
      _ExtentY        =   847
      _Version        =   393216
   End
   Begin MSFlexGridLib.MSFlexGrid gridData 
      Height          =   2055
      Left            =   240
      TabIndex        =   35
      Top             =   2880
      Width           =   10335
      _ExtentX        =   18230
      _ExtentY        =   3625
      _Version        =   393216
      FixedCols       =   0
      WordWrap        =   -1  'True
   End
   Begin VB.TextBox txtTaxRate 
      Height          =   285
      Left            =   1200
      TabIndex        =   33
      Top             =   1920
      Width           =   1095
   End
   Begin VB.CheckBox chkAfterTax 
      BackColor       =   &H00E0E0E0&
      Caption         =   "After Tax Analysis"
      Height          =   255
      Left            =   240
      TabIndex        =   31
      Top             =   1560
      Width           =   2415
   End
   Begin VB.OptionButton rdoTMC 
      BackColor       =   &H00E0E0E0&
      Height          =   255
      Left            =   4560
      TabIndex        =   30
      Top             =   2040
      Width           =   255
   End
   Begin VB.OptionButton rdoMVAE 
      BackColor       =   &H00E0E0E0&
      Height          =   375
      Left            =   4560
      TabIndex        =   29
      Top             =   600
      Value           =   -1  'True
      Width           =   255
   End
   Begin VB.Frame Frame2 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Enter MV / AE"
      Height          =   975
      Left            =   4920
      TabIndex        =   24
      Top             =   600
      Width           =   4095
      Begin VB.TextBox txtAE 
         BackColor       =   &H00FFFFFF&
         ForeColor       =   &H00000000&
         Height          =   285
         Left            =   2400
         TabIndex        =   26
         Top             =   600
         Width           =   1455
      End
      Begin VB.TextBox txtMV 
         BackColor       =   &H00FFFFFF&
         ForeColor       =   &H00000000&
         Height          =   285
         Left            =   2400
         TabIndex        =   25
         Top             =   240
         Width           =   1455
      End
      Begin VB.Label Label3 
         BackColor       =   &H00E0E0E0&
         Caption         =   "Annual Expenses:"
         ForeColor       =   &H00000000&
         Height          =   255
         Left            =   120
         TabIndex        =   28
         Top             =   600
         Width           =   2175
      End
      Begin VB.Label Label2 
         BackColor       =   &H00E0E0E0&
         Caption         =   "Market Value (MV) at EOY:"
         ForeColor       =   &H00000000&
         Height          =   255
         Left            =   120
         TabIndex        =   27
         Top             =   240
         Width           =   2175
      End
   End
   Begin VB.Frame Frame1 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Enter TMC"
      Height          =   735
      Left            =   4920
      TabIndex        =   21
      Top             =   1920
      Width           =   4095
      Begin VB.TextBox txtTMC 
         Height          =   285
         Left            =   2400
         TabIndex        =   23
         Top             =   240
         Width           =   1455
      End
      Begin VB.Label Label16 
         BackColor       =   &H00E0E0E0&
         Caption         =   "Total Marginal Costs (TMC)"
         Height          =   375
         Left            =   240
         TabIndex        =   22
         Top             =   240
         Width           =   2055
      End
   End
   Begin VB.TextBox txtMinEUAC 
      BackColor       =   &H00E0E0E0&
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   1920
      Locked          =   -1  'True
      TabIndex        =   17
      TabStop         =   0   'False
      Top             =   6120
      Width           =   1575
   End
   Begin VB.TextBox txtESL 
      BackColor       =   &H00E0E0E0&
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   2760
      Locked          =   -1  'True
      TabIndex        =   16
      TabStop         =   0   'False
      Top             =   5640
      Width           =   735
   End
   Begin VB.CommandButton butResult 
      Caption         =   "Result"
      Height          =   375
      Left            =   9240
      TabIndex        =   15
      Top             =   5760
      Width           =   1215
   End
   Begin VB.CommandButton butAdd 
      Caption         =   "Add"
      Height          =   375
      Left            =   9360
      TabIndex        =   1
      Top             =   720
      Width           =   855
   End
   Begin VB.TextBox txtEOY 
      BackColor       =   &H00E0E0E0&
      ForeColor       =   &H00000000&
      Height          =   285
      Left            =   1920
      Locked          =   -1  'True
      TabIndex        =   13
      Top             =   1080
      Width           =   735
   End
   Begin VB.ComboBox cmbProject 
      Height          =   315
      ItemData        =   "frmExpectedServiceLife.frx":0000
      Left            =   240
      List            =   "frmExpectedServiceLife.frx":000A
      Style           =   2  'Dropdown List
      TabIndex        =   12
      Top             =   240
      Width           =   3015
   End
   Begin VB.CommandButton butClearAll 
      Caption         =   "Clear All"
      Height          =   375
      Left            =   2040
      TabIndex        =   2
      Top             =   5040
      Width           =   1335
   End
   Begin VB.TextBox txtCostOfCapital 
      Alignment       =   1  'Right Justify
      BackColor       =   &H00E0E0E0&
      BorderStyle     =   0  'None
      ForeColor       =   &H00000000&
      Height          =   285
      Left            =   1920
      Locked          =   -1  'True
      TabIndex        =   0
      Top             =   720
      Width           =   495
   End
   Begin VB.Label Label18 
      BackColor       =   &H00E0E0E0&
      Caption         =   "%"
      Height          =   255
      Left            =   2400
      TabIndex        =   34
      Top             =   1920
      Width           =   375
   End
   Begin VB.Label Label17 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Rate:"
      Height          =   255
      Left            =   480
      TabIndex        =   32
      Top             =   1920
      Width           =   615
   End
   Begin VB.Label Label15 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Minimum EUAC:"
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   480
      TabIndex        =   20
      Top             =   6120
      Width           =   1335
   End
   Begin VB.Label Label14 
      BackColor       =   &H00E0E0E0&
      Caption         =   "years"
      ForeColor       =   &H00000000&
      Height          =   255
      Left            =   3600
      TabIndex        =   19
      Top             =   5640
      Width           =   855
   End
   Begin VB.Label Label13 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Economic Service Life (ESL):"
      ForeColor       =   &H00000000&
      Height          =   255
      Left            =   480
      TabIndex        =   18
      Top             =   5640
      Width           =   2175
   End
   Begin VB.Label Label1 
      BackColor       =   &H00E0E0E0&
      Caption         =   "End of Year (EOY): "
      ForeColor       =   &H00000000&
      Height          =   255
      Left            =   240
      TabIndex        =   14
      Top             =   1080
      Width           =   1815
   End
   Begin VB.Label Label12 
      Alignment       =   2  'Center
      BackColor       =   &H00E0E0E0&
      Caption         =   "Equivalent Uniform Costs (EUAC)"
      ForeColor       =   &H00000000&
      Height          =   615
      Left            =   7440
      TabIndex        =   11
      Top             =   3240
      Visible         =   0   'False
      Width           =   975
   End
   Begin VB.Label Label11 
      Alignment       =   2  'Center
      BackColor       =   &H00E0E0E0&
      Caption         =   "Total Marginal Cost (TMC)"
      ForeColor       =   &H00000000&
      Height          =   615
      Left            =   6240
      TabIndex        =   10
      Top             =   3240
      Visible         =   0   'False
      Width           =   855
   End
   Begin VB.Label Label10 
      Alignment       =   2  'Center
      BackColor       =   &H00E0E0E0&
      Caption         =   "Annual Expenses (AE)"
      ForeColor       =   &H00000000&
      Height          =   615
      Left            =   5280
      TabIndex        =   9
      Top             =   3240
      Visible         =   0   'False
      Width           =   735
   End
   Begin VB.Label Label9 
      Alignment       =   2  'Center
      BackColor       =   &H00E0E0E0&
      Caption         =   "Cost of Capital"
      ForeColor       =   &H00000000&
      Height          =   495
      Left            =   3960
      TabIndex        =   8
      Top             =   3360
      Visible         =   0   'False
      Width           =   855
   End
   Begin VB.Label Label8 
      Alignment       =   2  'Center
      BackColor       =   &H00E0E0E0&
      Caption         =   "Loss in MV"
      ForeColor       =   &H00000000&
      Height          =   495
      Left            =   2760
      TabIndex        =   7
      Top             =   3360
      Visible         =   0   'False
      Width           =   735
   End
   Begin VB.Label Label7 
      Alignment       =   2  'Center
      BackColor       =   &H00E0E0E0&
      Caption         =   "Market Value (MV)"
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   1320
      TabIndex        =   6
      Top             =   3360
      Visible         =   0   'False
      Width           =   855
   End
   Begin VB.Label Label6 
      Alignment       =   2  'Center
      BackColor       =   &H00E0E0E0&
      Caption         =   "End of Year (EOY)"
      ForeColor       =   &H00000000&
      Height          =   495
      Left            =   240
      TabIndex        =   5
      Top             =   3360
      Visible         =   0   'False
      Width           =   855
   End
   Begin VB.Label Label5 
      BackColor       =   &H00E0E0E0&
      Caption         =   "%"
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   2520
      TabIndex        =   4
      Top             =   720
      Width           =   375
   End
   Begin VB.Label Label4 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Cost of Capital:"
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   240
      TabIndex        =   3
      Top             =   720
      Width           =   1215
   End
End
Attribute VB_Name = "frmReplacement"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Option Explicit

Private Type RAGridRow
    iEOY As Currency
    iMV As Currency
    iLossInMV As Currency
    iCostOfCapital As Currency
    iAE As Currency
    iTMC As Currency
    iEUAC As Currency
End Type

Private Type Project
    RAGrid(32) As RAGridRow
    iEOYCount As Integer
    iCostOfCapital As Double
    iTaxRate As Double
    useAfterTax As Boolean
End Type

Dim theProjects(2) As Project

Public Sub miSave()
    Dim i As Integer
    Dim temp As String
    Dim strFileName As String
    Dim iOriginal As Integer
    
    CommonDialog1.Filter = "ENGINEA Files (*.eea)|*.eea|All Files (*.*)|*.*"
    CommonDialog1.FileName = "replacement.eea"
    CommonDialog1.ShowSave
    strFileName = CommonDialog1.FileName
    
    If (Trim(strFileName) <> "") Then
    
        Open strFileName For Output As #1
            Print #1, "[Replacement]"
            
            iOriginal = cmbProject.ListIndex

            
            '*** Save Challenger ***'
            Print #1, "[Challenger]"
            cmbProject.ListIndex = 0
            
            If (theProjects(0).useAfterTax) Then
                Print #1, "TRUE"
            Else
                Print #1, "FALSE"
            End If
            Print #1, theProjects(0).iCostOfCapital
            Print #1, theProjects(0).iTaxRate
            
            
            i = 0

            While (i < theProjects(0).iEOYCount)
                Print #1, theProjects(0).RAGrid(i).iAE
                Print #1, theProjects(0).RAGrid(i).iEUAC
                Print #1, theProjects(0).RAGrid(i).iLossInMV
                Print #1, theProjects(0).RAGrid(i).iMV
                Print #1, theProjects(0).RAGrid(i).iTMC
                i = i + 1
            Wend
            
            '*** Save Defender ***'
            Print #1, "[Defender]"
            cmbProject.ListIndex = 1
            
            If (theProjects(1).useAfterTax) Then
                Print #1, "TRUE"
            Else
                Print #1, "FALSE"
            End If
            Print #1, theProjects(1).iCostOfCapital
            Print #1, theProjects(1).iTaxRate
            
            
            i = 0

            While (i < theProjects(1).iEOYCount)
                Print #1, theProjects(1).RAGrid(i).iAE
                Print #1, theProjects(1).RAGrid(i).iEUAC
                Print #1, theProjects(1).RAGrid(i).iLossInMV
                Print #1, theProjects(1).RAGrid(i).iMV
                Print #1, theProjects(1).RAGrid(i).iTMC
                
                i = i + 1
            Wend

    
        Close #1
    
        cmbProject.ListIndex = iOriginal
    
    End If
    
End Sub
Public Sub miOpen(strFileName As String)

    Dim i As Integer
    Dim temp As String
'    Dim strFileName As String
    
'    CommonDialog1.Filter = "ECONS Files (*.eco)|*.eco|All Files (*.*)|*.*"
'    CommonDialog1.ShowOpen
    
'    strFileName = CommonDialog1.FileName
    
    
    If (Trim(strFileName) <> "") Then

        Open strFileName For Input As #1
        
        Line Input #1, temp
        
        If (temp = "[Replacement]") Then
            cmbProject.ListIndex = 0
            butClearAll_Click
            cmbProject.ListIndex = 1
            butClearAll_Click
            
            '*** Read Challenger Values ***'
            Line Input #1, temp
            If (temp = "[Challenger]") Then
                cmbProject.ListIndex = 0
            End If
            
            
            '*** Get After Tax ***'
            Line Input #1, temp
            If (temp = "TRUE") Then
                theProjects(0).useAfterTax = True
                chkAfterTax.Value = vbChecked
            ElseIf (temp = "FALSE") Then
                theProjects(0).useAfterTax = False
                chkAfterTax.Value = vbUnchecked
            End If
            
            Line Input #1, temp
            theProjects(0).iCostOfCapital = temp
            txtCostOfCapital.Text = theProjects(0).iCostOfCapital * 100#
            
            Line Input #1, temp
            theProjects(0).iTaxRate = temp
            txtTaxRate.Text = theProjects(0).iTaxRate * 100#
            
            i = 0
            Line Input #1, temp
            While ((temp <> "[Defender]") And (Not EOF(1)))
                
                theProjects(0).RAGrid(i).iAE = temp
                
                Line Input #1, temp
                theProjects(0).RAGrid(i).iEUAC = temp
                
                Line Input #1, temp
                theProjects(0).RAGrid(i).iLossInMV = temp
                
                Line Input #1, temp
                theProjects(0).RAGrid(i).iMV = temp
                
                Line Input #1, temp
                theProjects(0).RAGrid(i).iTMC = temp

                theProjects(0).RAGrid(i).iEOY = i

                i = i + 1
                Line Input #1, temp
                theProjects(0).iEOYCount = theProjects(0).iEOYCount + 1
            
            
            Wend
            
            
            
            '*** Get Defender Values ***'
            If (temp = "[Defender]") Then
                cmbProject.ListIndex = 1
                        
            End If
            
            
            '*** Get After Tax ***'
            Line Input #1, temp
            If (temp = "TRUE") Then
                theProjects(1).useAfterTax = True
                chkAfterTax.Value = vbChecked
            ElseIf (temp = "FALSE") Then
                theProjects(1).useAfterTax = False
                chkAfterTax.Value = vbUnchecked
            End If
            
            Line Input #1, temp
            theProjects(1).iCostOfCapital = temp
            txtCostOfCapital.Text = theProjects(1).iCostOfCapital * 100#
            
            Line Input #1, temp
            theProjects(1).iTaxRate = temp
            txtTaxRate.Text = theProjects(1).iTaxRate * 100#
            
            i = 0
            While (Not EOF(1))
                
                Line Input #1, temp
                theProjects(1).RAGrid(i).iAE = temp
                
                Line Input #1, temp
                theProjects(1).RAGrid(i).iEUAC = temp
                
                Line Input #1, temp
                theProjects(1).RAGrid(i).iLossInMV = temp
                
                Line Input #1, temp
                theProjects(1).RAGrid(i).iMV = temp
                
                Line Input #1, temp
                theProjects(1).RAGrid(i).iTMC = temp
                
                theProjects(1).RAGrid(i).iEOY = i

                i = i + 1
                theProjects(1).iEOYCount = theProjects(1).iEOYCount + 1
            
            Wend


'            CalculateGrid
    
        Else
            MsgBox "Not an ENGINEA Replacement Analysis Save File", vbInformation
        End If
        Close #1

    End If
    cmbProject.ListIndex = 0


End Sub

Public Sub miPrint()

    Dim i As Integer
    Dim iCol1 As Integer
    Dim iCol2 As Integer
    
    Dim LINE_SPACING As Integer
    Dim COLUMN_WIDTH As Integer
    Dim iLinePosition As Integer
    Dim iColPosition As Integer
    
    On Error GoTo handleError
    
    CommonDialogPrinter.ShowPrinter
    
    Printer.Print "Created with ENGINEA"
    
    
    
    Printer.CurrentY = 500
    
    iCol1 = 0
    
    Printer.CurrentX = iCol1
    
    LINE_SPACING = 200
    COLUMN_WIDTH = 1500
    iLinePosition = 1500
    iColPosition = 0
    
    
    '*** PRINT CHALLENGER ***'
    cmbProject.ListIndex = 0
    iLinePosition = iLinePosition + LINE_SPACING
    Printer.CurrentY = iLinePosition
    Printer.Print "Challenger: "
    
    iLinePosition = iLinePosition + LINE_SPACING
    Printer.CurrentY = iLinePosition
    Printer.Print "Cost of Capital: "; Tab(20); txtCostOfCapital.Text & "%"
            
    i = 0
    
    
    iColPosition = 0
    Printer.CurrentX = iColPosition
    iLinePosition = iLinePosition + LINE_SPACING
    Printer.CurrentY = iLinePosition
    Printer.Print "End of Year (EOY)";
    
    iColPosition = iColPosition + COLUMN_WIDTH
    Printer.CurrentX = iColPosition
    Printer.CurrentY = iLinePosition
    Printer.Print "Market Value (MV)"
    
    iColPosition = iColPosition + COLUMN_WIDTH
    Printer.CurrentX = iColPosition
    Printer.CurrentY = iLinePosition
    Printer.Print "Loss in MV"
          
    iColPosition = iColPosition + COLUMN_WIDTH
    Printer.CurrentX = iColPosition
    Printer.CurrentY = iLinePosition
    Printer.Print "Cost of Capital"
          
    iColPosition = iColPosition + COLUMN_WIDTH
    Printer.CurrentX = iColPosition
    Printer.CurrentY = iLinePosition
    Printer.Print "Annual Expenses"
          
    iColPosition = iColPosition + COLUMN_WIDTH
    Printer.CurrentX = iColPosition
    Printer.CurrentY = iLinePosition
    Printer.Print "TMC"
          
    iColPosition = iColPosition + COLUMN_WIDTH
    Printer.CurrentX = iColPosition
    Printer.CurrentY = iLinePosition
    Printer.Print "EUAC"


    iLinePosition = iLinePosition + LINE_SPACING
    While (i < theProjects(0).iEOYCount)
        iColPosition = 0
    
        Printer.CurrentX = iColPosition
        Printer.CurrentY = iLinePosition
        Printer.Print i & ""

    
        iColPosition = iColPosition + COLUMN_WIDTH
        Printer.CurrentX = iColPosition
        Printer.CurrentY = iLinePosition
        Printer.Print theProjects(0).RAGrid(i).iMV

        iColPosition = iColPosition + COLUMN_WIDTH
        Printer.CurrentX = iColPosition
        Printer.CurrentY = iLinePosition
        Printer.Print theProjects(0).RAGrid(i).iLossInMV
        
        iColPosition = iColPosition + COLUMN_WIDTH
        Printer.CurrentX = iColPosition
        Printer.CurrentY = iLinePosition
        Printer.Print theProjects(0).RAGrid(i).iCostOfCapital
        
        iColPosition = iColPosition + COLUMN_WIDTH
        Printer.CurrentX = iColPosition
        Printer.CurrentY = iLinePosition
        Printer.Print theProjects(0).RAGrid(i).iAE
        
        iColPosition = iColPosition + COLUMN_WIDTH
        Printer.CurrentX = iColPosition
        Printer.CurrentY = iLinePosition
        Printer.Print theProjects(0).RAGrid(i).iTMC
        
        iColPosition = iColPosition + COLUMN_WIDTH
        Printer.CurrentX = iColPosition
        Printer.CurrentY = iLinePosition
        Printer.Print theProjects(0).RAGrid(i).iEUAC
        
        iLinePosition = iLinePosition + LINE_SPACING
        i = i + 1
    Wend
    
    
    '*** PRINT DEFENDER ***'
    cmbProject.ListIndex = 1
    iLinePosition = iLinePosition + LINE_SPACING
    Printer.CurrentY = iLinePosition
    Printer.Print "Defender: "
    
    iLinePosition = iLinePosition + LINE_SPACING
    Printer.CurrentY = iLinePosition
    Printer.Print "Cost of Capital: "; Tab(20); txtCostOfCapital.Text & "%"
            
    i = 0
    
    iColPosition = 0
    Printer.CurrentX = iColPosition
    iLinePosition = iLinePosition + LINE_SPACING
    Printer.CurrentY = iLinePosition
    Printer.Print "End of Year (EOY)";
    
    iColPosition = iColPosition + COLUMN_WIDTH
    Printer.CurrentX = iColPosition
    Printer.CurrentY = iLinePosition
    Printer.Print "Market Value (MV)"
    
    iColPosition = iColPosition + COLUMN_WIDTH
    Printer.CurrentX = iColPosition
    Printer.CurrentY = iLinePosition
    Printer.Print "Loss in MV"
          
    iColPosition = iColPosition + COLUMN_WIDTH
    Printer.CurrentX = iColPosition
    Printer.CurrentY = iLinePosition
    Printer.Print "Cost of Capital"
          
    iColPosition = iColPosition + COLUMN_WIDTH
    Printer.CurrentX = iColPosition
    Printer.CurrentY = iLinePosition
    Printer.Print "Annual Expenses"
          
    iColPosition = iColPosition + COLUMN_WIDTH
    Printer.CurrentX = iColPosition
    Printer.CurrentY = iLinePosition
    Printer.Print "TMC"
          
    iColPosition = iColPosition + COLUMN_WIDTH
    Printer.CurrentX = iColPosition
    Printer.CurrentY = iLinePosition
    Printer.Print "EUAC"


    iLinePosition = iLinePosition + LINE_SPACING
    While (i < theProjects(1).iEOYCount)
        iColPosition = 0
    
        Printer.CurrentX = iColPosition
        Printer.CurrentY = iLinePosition
        Printer.Print i & ""

    
        iColPosition = iColPosition + COLUMN_WIDTH
        Printer.CurrentX = iColPosition
        Printer.CurrentY = iLinePosition
        Printer.Print theProjects(1).RAGrid(i).iMV

        iColPosition = iColPosition + COLUMN_WIDTH
        Printer.CurrentX = iColPosition
        Printer.CurrentY = iLinePosition
        Printer.Print theProjects(1).RAGrid(i).iLossInMV
        
        iColPosition = iColPosition + COLUMN_WIDTH
        Printer.CurrentX = iColPosition
        Printer.CurrentY = iLinePosition
        Printer.Print theProjects(1).RAGrid(i).iCostOfCapital
        
        iColPosition = iColPosition + COLUMN_WIDTH
        Printer.CurrentX = iColPosition
        Printer.CurrentY = iLinePosition
        Printer.Print theProjects(1).RAGrid(i).iAE
        
        iColPosition = iColPosition + COLUMN_WIDTH
        Printer.CurrentX = iColPosition
        Printer.CurrentY = iLinePosition
        Printer.Print theProjects(1).RAGrid(i).iTMC
        
        iColPosition = iColPosition + COLUMN_WIDTH
        Printer.CurrentX = iColPosition
        Printer.CurrentY = iLinePosition
        Printer.Print theProjects(1).RAGrid(i).iEUAC
        
        iLinePosition = iLinePosition + LINE_SPACING
        i = i + 1
    Wend
    
    
    
    
    Printer.EndDoc
    
    
handleError:
    'the user pressed Cancel
    Exit Sub


End Sub


Private Sub butAdd_Click()
'    If (Not IsNumeric(txtCostOfCapital.Text)) Then
'        MsgBox "Please Enter Cost of Capital", vbInformation
'    Else
'        txtCostOfCapital.Locked = True
'        txtCostOfCapital.BackColor = RGB(192, 192, 192)
    
    theProjects(cmbProject.ListIndex).iCostOfCapital = Val(txtCostOfCapital.Text) / 100#
    
    theProjects(cmbProject.ListIndex).RAGrid(theProjects(cmbProject.ListIndex).iEOYCount).iEOY = txtEOY.Text
    
    
    If (rdoMVAE.Value = True) Then
    
        If (txtMV.Text <> "") Then
            theProjects(cmbProject.ListIndex).RAGrid(theProjects(cmbProject.ListIndex).iEOYCount).iMV = txtMV.Text
        Else
            theProjects(cmbProject.ListIndex).RAGrid(theProjects(cmbProject.ListIndex).iEOYCount).iMV = 0
        End If
    
    
        If (theProjects(cmbProject.ListIndex).iEOYCount > 0) Then
        
            theProjects(cmbProject.ListIndex).RAGrid(theProjects(cmbProject.ListIndex).iEOYCount).iLossInMV = theProjects(cmbProject.ListIndex).RAGrid(theProjects(cmbProject.ListIndex).iEOYCount - 1).iMV - theProjects(cmbProject.ListIndex).RAGrid(theProjects(cmbProject.ListIndex).iEOYCount).iMV
            theProjects(cmbProject.ListIndex).RAGrid(theProjects(cmbProject.ListIndex).iEOYCount).iCostOfCapital = theProjects(cmbProject.ListIndex).RAGrid(theProjects(cmbProject.ListIndex).iEOYCount - 1).iMV * theProjects(cmbProject.ListIndex).iCostOfCapital
        Else
            theProjects(cmbProject.ListIndex).RAGrid(theProjects(cmbProject.ListIndex).iEOYCount).iLossInMV = 0
            theProjects(cmbProject.ListIndex).RAGrid(theProjects(cmbProject.ListIndex).iEOYCount).iCostOfCapital = 0
        End If
    
        If (txtAE.Text <> "") Then
            theProjects(cmbProject.ListIndex).RAGrid(theProjects(cmbProject.ListIndex).iEOYCount).iAE = txtAE.Text
        Else
            theProjects(cmbProject.ListIndex).RAGrid(theProjects(cmbProject.ListIndex).iEOYCount).iAE = 0
        End If
    
        theProjects(cmbProject.ListIndex).RAGrid(theProjects(cmbProject.ListIndex).iEOYCount).iTMC = theProjects(cmbProject.ListIndex).RAGrid(theProjects(cmbProject.ListIndex).iEOYCount).iLossInMV + theProjects(cmbProject.ListIndex).RAGrid(theProjects(cmbProject.ListIndex).iEOYCount).iCostOfCapital + theProjects(cmbProject.ListIndex).RAGrid(theProjects(cmbProject.ListIndex).iEOYCount).iAE
        
    ElseIf (rdoTMC.Value = True) Then
        
        theProjects(cmbProject.ListIndex).RAGrid(theProjects(cmbProject.ListIndex).iEOYCount).iTMC = txtTMC.Text
    
    End If
    
    
    If (theProjects(cmbProject.ListIndex).iEOYCount > 0) Then
        theProjects(cmbProject.ListIndex).RAGrid(theProjects(cmbProject.ListIndex).iEOYCount).iEUAC = GetEUAC(theProjects(cmbProject.ListIndex).iEOYCount)
    Else
        theProjects(cmbProject.ListIndex).RAGrid(theProjects(cmbProject.ListIndex).iEOYCount).iEUAC = 0
    End If
    
    
        
        theProjects(cmbProject.ListIndex).iEOYCount = theProjects(cmbProject.ListIndex).iEOYCount + 1
        CalculateGrid

        txtEOY.Text = theProjects(cmbProject.ListIndex).iEOYCount
        txtMV.Text = ""
        txtAE.Text = ""
        txtTMC.Text = ""
    
    If (rdoMVAE.Value = True) Then
        txtMV.SetFocus
    ElseIf (rdoTMC.Value = True) Then
        txtTMC.SetFocus
    End If

End Sub

Private Sub CalculateGrid()
    Dim i As Integer
    
    gridData.Rows = 1
    
    i = 0
    While (i < theProjects(cmbProject.ListIndex).iEOYCount)
                         
        gridData.AddItem theProjects(cmbProject.ListIndex).RAGrid(i).iEOY & vbTab & _
                         theProjects(cmbProject.ListIndex).RAGrid(i).iMV & vbTab & _
                         theProjects(cmbProject.ListIndex).RAGrid(i).iLossInMV & vbTab & _
                         theProjects(cmbProject.ListIndex).RAGrid(i).iCostOfCapital & vbTab & _
                         theProjects(cmbProject.ListIndex).RAGrid(i).iAE & vbTab & _
                         theProjects(cmbProject.ListIndex).RAGrid(i).iTMC & vbTab & _
                         theProjects(cmbProject.ListIndex).RAGrid(i).iEUAC
        i = i + 1
    Wend
                     
    FindESL
                     
    
End Sub


Private Sub butCancel_Click()
    Unload Me
End Sub

Private Sub butClearAll_Click()
    theProjects(cmbProject.ListIndex).iEOYCount = 0
    txtEOY.Text = theProjects(cmbProject.ListIndex).iEOYCount
    txtMV.Text = ""
    txtAE.Text = ""
    txtCostOfCapital.Text = ""
'    txtCostOfCapital.Locked = False
'    txtCostOfCapital.BackColor = RGB(255, 255, 255)
    gridData.Rows = 1


End Sub


Private Sub butCostOfCapital_Click()
    If (theProjects(cmbProject.ListIndex).iEOYCount > 0) Then
        MsgBox "Can not modify Cost of Capital, since grid values already exist", vbInformation
    Else
        txtCostOfCapital.Text = InputBox("Enter Cost of Capital", "Input", "0")
    End If
End Sub

Private Sub butRemoveLast_Click()
    theProjects(cmbProject.ListIndex).iEOYCount = theProjects(cmbProject.ListIndex).iEOYCount
    txtEOY.Text = theProjects(cmbProject.ListIndex).iEOYCount
    txtMV.Text = ""
    txtAE.Text = ""
    txtCostOfCapital.Text = ""
    gridData.Rows = gridData.Rows - 1

End Sub

Private Sub butResult_Click()
    Dim iDefEUAC As Double
    Dim iChalEUAC As Double
    
    Dim iOriginal As Integer
    
    If (theProjects(0).iEOYCount = 0) Then
        MsgBox "Please enter values for Challenger to determine EUAC"
    ElseIf (theProjects(1).iEOYCount = 0) Then
        MsgBox "Please enter values for Defender to determine EUAC"
    Else
    
        iOriginal = cmbProject.ListIndex
        
        cmbProject.ListIndex = 0
        iChalEUAC = txtMinEUAC.Text
    
        cmbProject.ListIndex = 1
        iDefEUAC = txtMinEUAC.Text
        
        cmbProject.ListIndex = iOriginal
    
        If (iDefEUAC > iChalEUAC) Then
            MsgBox "EUAC Challenger < EUAC Defender" & vbCrLf & iChalEUAC & " < " & iDefEUAC & vbCrLf & " Replace Defender with Challenger now", vbInformation, "Results"
        ElseIf (iChalEUAC > iDefEUAC) Then
            MsgBox "Keep Defender for at least one year", vbInformation, "Results"
        ElseIf (iChalEUAC = iDefEUAC) Then
            MsgBox "Defender Minimum EUAC equals Challenger EUAC.  Replace or Keep Defender", vbInformation
        End If
    End If

End Sub

Private Sub cmbProject_Click()
    txtCostOfCapital.Text = theProjects(cmbProject.ListIndex).iCostOfCapital * 100
    
    CalculateGrid

    txtEOY.Text = theProjects(cmbProject.ListIndex).iEOYCount
    txtMV.Text = ""
    txtAE.Text = ""
    

End Sub

Private Sub Form_Load()


'    txtCostOfCapital.Locked = False
'    txtCostOfCapital.BackColor = RGB(255, 255, 255)
    
    
    cmbProject.Text = "Challenger"
    
    theProjects(0).iEOYCount = 0
    theProjects(1).iEOYCount = 0
    
    txtEOY.Text = theProjects(cmbProject.ListIndex).iEOYCount
    
    gridData.FormatString = "End of Year (EOY)" & vbTab & _
                            "Market Value (MV)" & vbTab & _
                            "Loss in MV       " & vbTab & _
                            "Cost of Capital  " & vbTab & _
                            "Annual Expenses  " & vbTab & _
                            "TMC              " & vbTab & _
                            "EUAC             "

    gridData.Rows = 1
    
End Sub

Private Function GetEUAC(iYear As Integer) As Currency
    Dim i As Integer
    Dim iReturn As Currency
    
    i = 1
    iReturn = 0
    
    While (i <= iYear)
        
       iReturn = iReturn + (theProjects(cmbProject.ListIndex).RAGrid(i).iTMC * Constants.PGivenF(theProjects(cmbProject.ListIndex).iCostOfCapital, i * 1#))
       i = i + 1
    Wend
    
    iReturn = iReturn * Constants.AGivenP(theProjects(cmbProject.ListIndex).iCostOfCapital, iYear * 1#)
    
    GetEUAC = Round(iReturn, 0)

End Function

Private Sub FindESL()
    Dim iValue As Currency
    Dim iValueYear As Integer
    Dim i As Integer
    Dim isGreater As Boolean
    
    If (theProjects(cmbProject.ListIndex).iEOYCount - 1 >= 1) Then
        i = 1
        iValue = theProjects(cmbProject.ListIndex).RAGrid(i).iEUAC
        iValueYear = i
        i = i + 1
        isGreater = False
        
        While ((i < theProjects(cmbProject.ListIndex).iEOYCount) And (Not isGreater))
            If (theProjects(cmbProject.ListIndex).RAGrid(i).iEUAC < iValue) Then
                iValue = theProjects(cmbProject.ListIndex).RAGrid(i).iEUAC
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




Private Sub txtAE_GotFocus()
    rdoMVAE.Value = True

End Sub



Private Sub txtMV_GotFocus()
    rdoMVAE.Value = True
End Sub

Private Sub txtMV_KeyPress(KeyAscii As Integer)
    If (KeyAscii = 13) Then
        butAdd_Click
    End If
End Sub
Private Sub txtAE_KeyPress(KeyAscii As Integer)
    If (KeyAscii = 13) Then
        butAdd_Click
    End If
End Sub
Private Sub txtTMC_KeyPress(KeyAscii As Integer)
    If (KeyAscii = 13) Then
        butAdd_Click
    End If
End Sub


Private Sub txtTMC_GotFocus()
    rdoTMC.Value = True
End Sub

