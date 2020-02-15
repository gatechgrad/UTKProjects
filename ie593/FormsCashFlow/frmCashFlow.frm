VERSION 5.00
Object = "{F9043C88-F6F2-101A-A3C9-08002B2F49FB}#1.2#0"; "comdlg32.ocx"
Object = "{5E9E78A0-531B-11CF-91F6-C2863C385E30}#1.0#0"; "msflxgrd.ocx"
Begin VB.Form frmCashFlow 
   BackColor       =   &H00E0E0E0&
   Caption         =   "Cash Flow"
   ClientHeight    =   6810
   ClientLeft      =   315
   ClientTop       =   600
   ClientWidth     =   11580
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   MDIChild        =   -1  'True
   ScaleHeight     =   6810
   ScaleWidth      =   11580
   Begin MSComDlg.CommonDialog CommonDialogPrinter 
      Left            =   10800
      Top             =   5640
      _ExtentX        =   847
      _ExtentY        =   847
      _Version        =   393216
      CancelError     =   -1  'True
   End
   Begin VB.Frame Frame2 
      BackColor       =   &H00E0E0E0&
      Height          =   1695
      Left            =   3120
      TabIndex        =   13
      Top             =   4560
      Width           =   5655
      Begin VB.CommandButton butAdd 
         Caption         =   "Add"
         Height          =   375
         Left            =   3960
         TabIndex        =   22
         Top             =   240
         Width           =   1095
      End
      Begin VB.TextBox txtPeriod 
         BackColor       =   &H00E0E0E0&
         Height          =   285
         Left            =   960
         Locked          =   -1  'True
         TabIndex        =   21
         Top             =   720
         Width           =   975
      End
      Begin VB.TextBox txtCashFlow 
         Height          =   285
         Left            =   1320
         TabIndex        =   20
         Top             =   1200
         Width           =   1455
      End
      Begin VB.TextBox txtInterest 
         Alignment       =   1  'Right Justify
         BackColor       =   &H00E0E0E0&
         BorderStyle     =   0  'None
         Height          =   255
         Left            =   960
         TabIndex        =   19
         Top             =   240
         Width           =   615
      End
      Begin VB.CommandButton butInterest 
         Caption         =   "Select"
         Height          =   375
         Left            =   2040
         TabIndex        =   18
         Top             =   240
         Width           =   1095
      End
      Begin VB.CommandButton butUpdate 
         Caption         =   "Update"
         Height          =   375
         Left            =   3960
         TabIndex        =   17
         Top             =   720
         Width           =   1095
      End
      Begin VB.CommandButton butPrev 
         Caption         =   "<"
         Height          =   255
         Left            =   2040
         TabIndex        =   16
         Top             =   720
         Width           =   375
      End
      Begin VB.CommandButton butNext 
         Caption         =   ">"
         Height          =   255
         Left            =   2520
         TabIndex        =   15
         Top             =   720
         Width           =   375
      End
      Begin VB.CommandButton butRemove 
         Caption         =   "Remove"
         Height          =   375
         Left            =   3960
         TabIndex        =   14
         Top             =   1200
         Width           =   1095
      End
      Begin VB.Label Label1 
         BackColor       =   &H00E0E0E0&
         Caption         =   "Period:"
         Height          =   375
         Left            =   120
         TabIndex        =   26
         Top             =   720
         Width           =   855
      End
      Begin VB.Label Label2 
         BackColor       =   &H00E0E0E0&
         Caption         =   "Cash Flow:"
         Height          =   255
         Left            =   120
         TabIndex        =   25
         Top             =   1200
         Width           =   1095
      End
      Begin VB.Label Label6 
         BackColor       =   &H00E0E0E0&
         Caption         =   "Interest:"
         Height          =   255
         Left            =   120
         TabIndex        =   24
         Top             =   240
         Width           =   735
      End
      Begin VB.Label Label7 
         BackColor       =   &H00E0E0E0&
         Caption         =   "%"
         Height          =   255
         Left            =   1680
         TabIndex        =   23
         Top             =   240
         Width           =   255
      End
   End
   Begin VB.Frame Frame1 
      BackColor       =   &H00E0E0E0&
      Height          =   1695
      Left            =   120
      TabIndex        =   6
      Top             =   4560
      Width           =   2775
      Begin VB.TextBox txtPW 
         BackColor       =   &H00E0E0E0&
         BorderStyle     =   0  'None
         Height          =   375
         Left            =   960
         TabIndex        =   9
         TabStop         =   0   'False
         Top             =   240
         Width           =   1575
      End
      Begin VB.TextBox txtFW 
         BackColor       =   &H00E0E0E0&
         BorderStyle     =   0  'None
         Height          =   375
         Left            =   960
         TabIndex        =   8
         TabStop         =   0   'False
         Top             =   600
         Width           =   1575
      End
      Begin VB.TextBox txtAW 
         BackColor       =   &H00E0E0E0&
         BorderStyle     =   0  'None
         Height          =   375
         Left            =   960
         TabIndex        =   7
         TabStop         =   0   'False
         Top             =   960
         Width           =   1575
      End
      Begin VB.Label Label3 
         BackColor       =   &H00E0E0E0&
         Caption         =   "PW = "
         Height          =   375
         Left            =   120
         TabIndex        =   12
         Top             =   240
         Width           =   735
      End
      Begin VB.Label Label4 
         BackColor       =   &H00E0E0E0&
         Caption         =   "FW ="
         Height          =   375
         Left            =   120
         TabIndex        =   11
         Top             =   600
         Width           =   735
      End
      Begin VB.Label Label5 
         BackColor       =   &H00E0E0E0&
         Caption         =   "AW ="
         Height          =   375
         Left            =   120
         TabIndex        =   10
         Top             =   960
         Width           =   735
      End
   End
   Begin MSFlexGridLib.MSFlexGrid gridValues 
      Height          =   735
      Left            =   10440
      TabIndex        =   5
      Top             =   240
      Visible         =   0   'False
      Width           =   975
      _ExtentX        =   1720
      _ExtentY        =   1296
      _Version        =   393216
      FixedCols       =   0
   End
   Begin VB.HScrollBar HScroll1 
      Height          =   255
      Left            =   120
      TabIndex        =   4
      Top             =   4080
      Width           =   11055
   End
   Begin VB.PictureBox OuterPic 
      Height          =   3975
      Left            =   120
      ScaleHeight     =   3915
      ScaleWidth      =   10995
      TabIndex        =   2
      Top             =   120
      Width           =   11055
      Begin VB.PictureBox Picture1 
         AutoRedraw      =   -1  'True
         BackColor       =   &H00FFFFFF&
         FillStyle       =   0  'Solid
         ForeColor       =   &H00000000&
         Height          =   3975
         Left            =   0
         ScaleHeight     =   3915
         ScaleWidth      =   17055
         TabIndex        =   3
         TabStop         =   0   'False
         Top             =   0
         Width           =   17115
      End
   End
   Begin VB.CommandButton butClear 
      Caption         =   "Clear"
      Height          =   375
      Left            =   9720
      TabIndex        =   0
      Top             =   4560
      Width           =   1095
   End
   Begin VB.TextBox txtGrid 
      Height          =   735
      Left            =   9120
      MultiLine       =   -1  'True
      ScrollBars      =   2  'Vertical
      TabIndex        =   1
      TabStop         =   0   'False
      Top             =   5520
      Visible         =   0   'False
      Width           =   1095
   End
   Begin MSComDlg.CommonDialog CommonDialog1 
      Left            =   10800
      Top             =   5040
      _ExtentX        =   847
      _ExtentY        =   847
      _Version        =   393216
   End
End
Attribute VB_Name = "frmCashFlow"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Option Explicit

Const iMaxValues = 32

Dim iPeriod As Long
Dim theGrid(iMaxValues) As Currency

Dim clrBaseline
Dim clrArrow
Dim clrArrowHighlight
Dim clrValue
Dim clrValueHighlight
Dim clrPeriod
Dim clrPeriodHighlight


Public Sub miSave()
    Dim i As Integer
    Dim temp As String
    Dim strFileName As String
    
    CommonDialog1.Filter = "ENGINEA Files (*.eea)|*.eea|All Files (*.*)|*.*"
    CommonDialog1.FileName = "cashflow.eea"
    CommonDialog1.ShowSave
    strFileName = CommonDialog1.FileName
    
    'strFileName = InputBox("Enter File Name", "Input", "CashFlow.eea")
    
    If (Trim(strFileName) <> "") Then
    
        Open strFileName For Output As #1
            Print #1, "[Cash Flow]"
            
            Print #1, txtInterest.Text
        
            i = 0
            While (i < iPeriod)
                Print #1, theGrid(i)
                i = i + 1
            Wend
    
        Close #1
    
'        MsgBox "Saved Cash Flow to " & strFileName
    
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
        If (temp = "[Cash Flow]") Then
            butClear_Click
            
            '*** Get Interest ***'
            Line Input #1, temp
            txtInterest.Text = temp

            '*** Get Cash Flow Values ***'
            While Not EOF(1)
                Line Input #1, temp
                txtCashFlow.Text = temp
                butAdd_Click
            
            Wend
    
        Else
            MsgBox "Not an ENGINEA Cash Flow Save File", vbInformation

    
        End If
        Close #1

    End If


End Sub

Public Sub miOptions()
    frmCashFlowOptions.Show vbModal
End Sub

Private Sub butAdd_Click()

    If (IsNumeric(txtCashFlow.Text)) Then

        theGrid(iPeriod) = txtCashFlow.Text
        iPeriod = iPeriod + 1
        txtPeriod.Text = iPeriod

        butPrev.Enabled = True
        butNext.Enabled = False
    
    
        UpdatePeriod
        drawGraph
        CalculateValues
    
    
'    Picture1.DrawMode = vbInvert
        txtCashFlow.SetFocus
        txtCashFlow.Text = ""
        
        
        If (iPeriod > iMaxValues) Then
            butPrev_Click
        End If
        
    Else
        MsgBox "Invalid Cash Flow Value", vbInformation, "Invalid Value"
    End If
    
    
End Sub

Private Sub DrawArrow(pic As PictureBox, iMagnitude As Integer, x As Integer, y As Integer, strValue As String, strPeriod As String, highlightArrow As Boolean)
    If (highlightArrow) Then
        Picture1.ForeColor = clrArrowHighlight
    Else
        Picture1.ForeColor = clrArrow
    End If
    
    pic.Line (x, y)-(x, y + iMagnitude)
    
    If (iMagnitude < 0) Then
        pic.Line (x, y + iMagnitude)-(x - 64, y + iMagnitude + 64)
        pic.Line (x, y + iMagnitude)-(x + 64, y + iMagnitude + 64)
        pic.CurrentY = pic.CurrentY - 250
    ElseIf (iMagnitude > 0) Then
        pic.Line (x, y + iMagnitude)-(x - 64, y + iMagnitude - 64)
        pic.Line (x, y + iMagnitude)-(x + 64, y + iMagnitude - 64)
        pic.CurrentY = pic.CurrentY + 50
    ElseIf (iMagnitude = 0) Then
        pic.CurrentY = pic.CurrentY + 50

    End If
    
    If (iMagnitude <> 0) Then
        If (highlightArrow) Then
            Picture1.ForeColor = clrValueHighlight
        Else
            Picture1.ForeColor = clrValue
        End If

    
        pic.Print Abs(strValue)
    End If

    If (highlightArrow) Then
        Picture1.ForeColor = clrPeriodHighlight
    Else
        Picture1.ForeColor = clrPeriod
    End If

    pic.CurrentX = x
    pic.CurrentY = y + 50
    pic.Print strPeriod


End Sub


Private Sub butClear_Click()
    Dim i As Integer
    
    i = 0
    While (i < iMaxValues)
        theGrid(i) = 0
        i = i + 1
    Wend
    
    iPeriod = 0
    txtPeriod.Text = iPeriod
    txtPW.Text = ""
    txtFW.Text = ""
    txtAW.Text = ""

    
    butPrev.Enabled = False
    butNext.Enabled = False
    butUpdate.Enabled = False
    butAdd.Enabled = True
    
    
    UpdatePeriod
    drawGraph
    
    
End Sub

Private Sub butInterest_Click()
    txtInterest.Text = InputBox("Enter Interest Amount", "Interest", 0)
    CalculateValues
End Sub

Private Sub butNext_Click()
    Dim i As Integer
    
    i = txtPeriod.Text
    i = i + 1
    
    If (i >= iPeriod) Then
        butUpdate.Enabled = False
        butAdd.Enabled = True
        butNext.Enabled = False
        txtCashFlow.Text = ""
    Else
        txtCashFlow.Text = theGrid(i)
    End If
    
    If (i > 0) Then
        butPrev.Enabled = True
    End If
    
    txtPeriod.Text = i
            
    drawGraph
        
End Sub

Private Sub butPrev_Click()
    Dim i As Integer
    
    i = txtPeriod.Text
    
    i = i - 1
    
    If (i <= 0) Then
        butPrev.Enabled = False
    End If
    
    If (i < iPeriod) Then
        butNext.Enabled = True
    End If
    
    txtCashFlow = theGrid(i)

    txtPeriod.Text = i
    
    If (i < iPeriod) Then
        butUpdate.Enabled = True
        butAdd.Enabled = False
    End If
    
    drawGraph
    
End Sub

Private Sub butRemove_Click()
    Dim i As Integer
    Dim j As Integer
    Dim result
    
    i = txtPeriod.Text
    result = MsgBox("Remove Cash Flow for Period " & i & " and all following periods?", vbYesNo, "Remove")
    
    If (result = vbYes) Then
        j = i
        While (j < iPeriod)
            theGrid(j) = 0
            j = j + 1
        Wend
        
        iPeriod = i
    End If
    
    UpdatePeriod
    CalculateValues
    drawGraph
    
End Sub

Private Sub butUpdate_Click()
    Dim i As Integer
    
    i = txtPeriod.Text
    theGrid(i) = txtCashFlow
    
    UpdatePeriod
    drawGraph
    CalculateValues
    
    
    
End Sub

'Private Sub EnableMenus()
'    frmMain.miSave.Enabled = True
'    frmMain.miOpen.Enabled = True
'    frmMain.miPrint.Enabled = True
'    frmMain.miOptions.Enabled = True
'End Sub
'Private Sub DisableMenus()
'    frmMain.miSave.Enabled = False
'    frmMain.miOpen.Enabled = False
'    frmMain.miPrint.Enabled = False
'    frmMain.miOptions.Enabled = False
'End Sub


Private Sub Form_Activate()
    gridValues.FormatString = "Period              |Net Cash Flow       "
'    EnableMenus
    Setup_Scroll
End Sub

'Private Sub Form_Deactivate()
'    DisableMenus
'End Sub


'Private Sub Form_Unload(Cancel As Integer)
'    DisableMenus
'End Sub

Private Sub Form_Load()
    iPeriod = 0
    txtPeriod.Text = iPeriod

    txtInterest.Text = iPeriod
    
    butPrev.Enabled = False
    butNext.Enabled = False
    butUpdate.Enabled = False
    butAdd.Enabled = True
    
    
    clrBaseline = RGB(0, 0, 0)
    clrArrow = RGB(0, 128, 0)
    clrArrowHighlight = RGB(128, 0, 0)
    clrValue = RGB(0, 0, 0)
    clrValueHighlight = RGB(128, 0, 0)
    clrPeriod = RGB(128, 128, 128)
    clrPeriodHighlight = RGB(255, 128, 128)
    
    
    UpdatePeriod
    drawGraph
    
'    txtCashFlow.SetFocus
End Sub

Private Sub UpdatePeriod()
    Dim i As Integer
    
    txtGrid.Text = ""
    
    i = 0
    While (i < iPeriod)
        txtGrid.Text = txtGrid.Text & i & " " & theGrid(i) & vbCrLf
        i = i + 1
    Wend
 
    '*** Populate the grid ***'
    i = 0
    
    gridValues.Rows = 1
    While (i < iPeriod)
        
        gridValues.AddItem i & vbTab & Format(theGrid(i), "0.00")
        i = i + 1
    Wend

    
'     txtPeriod.Text = iPeriod
End Sub


Private Sub drawGraph()
    Dim i As Integer
    
    Dim iBaseline As Integer
    Dim iOffset As Integer
    Dim iWidth As Integer
    Dim iSpacing As Integer
    
    Dim iMagnitude As Long
    Dim highlightArrow As Boolean
    
    Picture1.Cls
    
    
    If (iPeriod > 0) Then
      iBaseline = 2000
      iOffset = 500
      iWidth = 2000
      iSpacing = 500
    
    
    
      Picture1.ForeColor = clrBaseline
      Picture1.Line (iOffset, iBaseline)-(iOffset + ((iPeriod - 1) * iSpacing), iBaseline)
    
    
      iMagnitude = 1
      i = 0
      While (i < iPeriod)
          If (Abs(theGrid(i)) > iMagnitude) Then
              iMagnitude = Abs(theGrid(i))
          End If
          i = i + 1
      Wend
    
    
      i = 0
      While (i < iPeriod)
          highlightArrow = False
          If (i = Val(txtPeriod.Text)) Then
              highlightArrow = True
          End If
          
          DrawArrow Picture1, theGrid(i) * 1500 / iMagnitude * -1, iOffset + (i * iSpacing), iBaseline, theGrid(i) & "", i & "", highlightArrow
          i = i + 1
      Wend
    End If


End Sub

Private Sub CalculateValues()
    Dim i As Integer
    Dim iPW As Currency
    Dim iAW As Currency
    Dim iFW As Currency
    
    
    Dim iInterest As Double
    
    iInterest = txtInterest.Text / 100
    
    i = 0
    
    iPW = 0
    iAW = 0
    iFW = 0
    
    While (i < iPeriod)
        iPW = iPW + (theGrid(i) * Constants.PGivenF(iInterest, i + 0#))
        
'        iAW = iAW + (theGrid(i) * Constants.AGivenF(iInterest, (iPeriod - 1) + 0#))
        iFW = iFW + (theGrid(i) * Constants.FGivenP(iInterest, (iPeriod - i - 1) + 0#))
        i = i + 1
    Wend
    
    txtPW.Text = Format(iPW, "0.00")
    txtFW.Text = Format(iFW, "0.00")
    
    
    'If (iPW > 0) Then
    '    txtAW.Text = iAW * Constants.AGivenP(iInterest, iPeriod - 1)
    'End If
    
    If (iPeriod > 1) Then
        
'        If (iInterest > 0) Then
            iAW = iPW * Constants.AGivenP(iInterest, iPeriod - 1)
        
'        ElseIf (iInterest = 0) Then
'            iAW = iPW / (iPeriod - 1)
'        End If
        
        txtAW.Text = Format(iAW, "0.00")
    
    Else
        txtAW.Text = "N/A"
    
    End If



End Sub


Public Sub miPrint()
    Dim i As Integer
    Dim iCol1 As Integer
    Dim iCol2 As Integer
    
    
    On Error GoTo handleError
    
    CommonDialogPrinter.ShowPrinter
    
    Printer.Print "Created with ENGINEA"
    
    
    Printer.PaintPicture Picture1.Image, 0, 200
        
    Printer.Line (0, 4000)-(Printer.ScaleWidth, 4000)
    
    Printer.CurrentY = 4100
    
    iCol1 = 0
    iCol2 = 1000
    
    Printer.CurrentX = iCol1
    Printer.Print "PW: "; Tab(15); txtPW.Text
    Printer.Print "FW: "; Tab(15); txtFW.Text
    Printer.Print "AW: "; Tab(15); txtAW.Text
    Printer.Print "i:  "; Tab(15); txtInterest.Text & "%"
    
    
    Printer.Print ""
    
    Printer.Line (0, Printer.CurrentY)-(Printer.ScaleWidth, Printer.CurrentY)
    
    Printer.Print ""
    
    Printer.CurrentX = iCol1
    Printer.Print "Period"; Tab(15); "Cash Flow"
    
    i = 0
    While (i < iPeriod)
        Printer.Print i; Tab(15); theGrid(i)

'        Printer.CurrentY = Printer.CurrentY - Printer.
        
        i = i + 1
    Wend
    
    Printer.EndDoc
    
handleError:
    'the user pressed Cancel
    Exit Sub
End Sub

Private Sub save_Click()
    Dim img As Image
    
'    img = Picture1.Image
    
'    img.save ("test.jpg")
'    Call SavePicture(Picture1.Picture, "test.bmp")
    
End Sub


Private Sub HScroll1_Change()
    Picture1.Left = HScroll1.Value
End Sub

Private Sub HScroll1_Scroll()
    Picture1.Left = HScroll1.Value
End Sub

Private Sub Setup_Scroll()
    HScroll1.Min = 0
    HScroll1.Max = OuterPic.ScaleWidth - Picture1.Width
    HScroll1.LargeChange = OuterPic.ScaleWidth
    HScroll1.SmallChange = OuterPic.ScaleWidth / 5
End Sub

'Private Sub Toolbar1_ButtonClick(ByVal Button As MSComctlLib.Button)
'    If (Button.Index = 1) Then
'        miOpen
'    ElseIf (Button.Index = 2) Then
'        miSave
        
'    ElseIf (Button.Index = 3) Then
'        miPrint
           
'    ElseIf (Button.Index = 4) Then
'        miOptions
    
'    End If

'End Sub

Private Sub txtCashFlow_KeyPress(KeyAscii As Integer)
    If (KeyAscii = 13) Then
        If (butAdd.Enabled = True) Then
            butAdd_Click
        ElseIf (butUpdate.Enabled = True) Then
            butUpdate_Click
        End If
        
        
    End If
End Sub

