VERSION 5.00
Object = "{F9043C88-F6F2-101A-A3C9-08002B2F49FB}#1.2#0"; "comdlg32.ocx"
Object = "{5E9E78A0-531B-11CF-91F6-C2863C385E30}#1.0#0"; "msflxgrd.ocx"
Begin VB.Form frmDepreciation 
   BackColor       =   &H00E0E0E0&
   Caption         =   "Depreciation Analysis"
   ClientHeight    =   6630
   ClientLeft      =   60
   ClientTop       =   345
   ClientWidth     =   8295
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   MDIChild        =   -1  'True
   ScaleHeight     =   6630
   ScaleWidth      =   8295
   Begin MSComDlg.CommonDialog CommonDialogPrinter 
      Left            =   6360
      Top             =   3840
      _ExtentX        =   847
      _ExtentY        =   847
      _Version        =   393216
   End
   Begin MSComDlg.CommonDialog CommonDialog1 
      Left            =   5640
      Top             =   3840
      _ExtentX        =   847
      _ExtentY        =   847
      _Version        =   393216
   End
   Begin VB.CommandButton butGraph 
      Caption         =   "View Graph"
      Height          =   375
      Left            =   4920
      TabIndex        =   15
      Top             =   5400
      Visible         =   0   'False
      Width           =   1455
   End
   Begin MSFlexGridLib.MSFlexGrid theGrid 
      Height          =   2295
      Left            =   360
      TabIndex        =   13
      Top             =   4080
      Width           =   4335
      _ExtentX        =   7646
      _ExtentY        =   4048
      _Version        =   393216
      Cols            =   3
      FixedCols       =   0
      ScrollTrack     =   -1  'True
      FocusRect       =   0
      SelectionMode   =   1
      AllowUserResizing=   1
   End
   Begin VB.CommandButton butCalculate 
      Caption         =   "Calculate"
      Height          =   375
      Left            =   4920
      TabIndex        =   12
      Top             =   4800
      Width           =   1455
   End
   Begin VB.TextBox txtPeriods 
      Height          =   285
      Left            =   2160
      TabIndex        =   3
      Top             =   3480
      Width           =   975
   End
   Begin VB.TextBox txtDisplay 
      Height          =   1935
      Left            =   6840
      MultiLine       =   -1  'True
      ScrollBars      =   2  'Vertical
      TabIndex        =   4
      Top             =   4560
      Visible         =   0   'False
      Width           =   1455
   End
   Begin VB.TextBox txtSalvageValue 
      Height          =   285
      Left            =   2160
      TabIndex        =   2
      Top             =   3000
      Width           =   2655
   End
   Begin VB.TextBox txtInitialCost 
      Height          =   285
      Left            =   2160
      TabIndex        =   1
      Top             =   2520
      Width           =   2655
   End
   Begin VB.Frame Frame1 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Depreciation Methods"
      Height          =   2055
      Left            =   120
      TabIndex        =   0
      Top             =   360
      Width           =   7575
      Begin VB.TextBox txtDB 
         Height          =   285
         Left            =   2400
         TabIndex        =   16
         Top             =   1440
         Width           =   975
      End
      Begin VB.OptionButton rdoDB 
         BackColor       =   &H00E0E0E0&
         Caption         =   "Declining Balance (DB)"
         Height          =   255
         Left            =   240
         TabIndex        =   14
         Top             =   1440
         Width           =   2895
      End
      Begin VB.OptionButton rdoMACRS 
         BackColor       =   &H00E0E0E0&
         Caption         =   "Modified Accelerated Cost Recovery System (MACRS)"
         Height          =   375
         Left            =   3000
         TabIndex        =   8
         Top             =   960
         Width           =   4335
      End
      Begin VB.OptionButton rdoDDB 
         BackColor       =   &H00E0E0E0&
         Caption         =   "Double Declining Balance (DDB)"
         Height          =   375
         Left            =   3000
         TabIndex        =   7
         Top             =   480
         Width           =   2775
      End
      Begin VB.OptionButton rdoSOYD 
         BackColor       =   &H00E0E0E0&
         Caption         =   "Sum of Years Digits (SOYD)"
         Height          =   375
         Left            =   240
         TabIndex        =   6
         Top             =   960
         Width           =   3615
      End
      Begin VB.OptionButton rdoSL 
         BackColor       =   &H00E0E0E0&
         Caption         =   "Straight Line (SL)"
         Height          =   375
         Left            =   240
         TabIndex        =   5
         Top             =   480
         Value           =   -1  'True
         Width           =   2415
      End
   End
   Begin VB.Label Label3 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Life"
      Height          =   375
      Left            =   360
      TabIndex        =   11
      Top             =   3480
      Width           =   1575
   End
   Begin VB.Label Label2 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Salvage Value"
      Height          =   375
      Left            =   360
      TabIndex        =   10
      Top             =   3120
      Width           =   1575
   End
   Begin VB.Label Label1 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Initial Cost"
      Height          =   375
      Left            =   360
      TabIndex        =   9
      Top             =   2640
      Width           =   1575
   End
End
Attribute VB_Name = "frmDepreciation"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Dim DISABLED_COLOR
Dim ACTIVE_COLOR


Private Type DepreciationValues
    iDepreciation As Currency
    iBookValue As Currency
End Type

Dim iValues() As DepreciationValues


Public Sub miSave()

    Dim i As Integer
    Dim strFileName As String
    
    CommonDialog1.Filter = "ENGINEA Files (*.eea)|*.eea|All Files (*.*)|*.*"
    CommonDialog1.FileName = "depreciation.eea"
    CommonDialog1.ShowSave
    strFileName = CommonDialog1.FileName
    
    If (Trim(strFileName) <> "") Then
    
        Open strFileName For Output As #1
        Print #1, "[Depreciation]"
        
        If (rdoSL.Value = True) Then
            Print #1, "SL"
        ElseIf (rdoSOYD.Value = True) Then
            Print #1, "SOYD"
        ElseIf (rdoDB.Value = True) Then
            Print #1, "DB"
        ElseIf (rdoDDB.Value = True) Then
            Print #1, "DDB"
        ElseIf (rdoMACRS.Value = True) Then
            Print #1, "MACRS"
        Else
            Print #1, "SL"
        End If
        
        Print #1, txtDB.Text
        
        Print #1, txtInitialCost.Text
        Print #1, txtSalvageValue.Text
        Print #1, txtPeriods.Text
            
        Close #1
    
    
    End If
End Sub

Public Sub miOpen(strFileName As String)
    Dim i As Integer
    Dim temp As String
    
    
    If (Trim(strFileName) <> "") Then

        Open strFileName For Input As #1
        
        Line Input #1, temp
        If (temp = "[Depreciation]") Then
            
            Line Input #1, temp
            Select Case temp
                Case "SL"
                    rdoSL.Value = True
                Case "SOYD"
                    rdoSOYD.Value = True
                Case "DB"
                    rdoDB.Value = True
                Case "DDB"
                    rdoDDB.Value = True
                Case "MACRS"
                    rdoMACRS.Value = True
                Case Else
                    rdoSL.Value = True
            End Select
            
            Line Input #1, temp
            txtDB.Text = temp

            Line Input #1, temp
            txtInitialCost.Text = temp

            Line Input #1, temp
            txtSalvageValue.Text = temp

            Line Input #1, temp
            txtPeriods.Text = temp


        End If
        Close #1

    End If


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
    
    
    iLinePosition = iLinePosition + LINE_SPACING
    Printer.CurrentY = iLinePosition
    
    If (rdoSL.Value = True) Then
        Printer.Print "Method: "; Tab(20); "SL"
    ElseIf (rdoSOYD.Value = True) Then
        Printer.Print "Method: "; Tab(20); "SOYD"
    ElseIf (rdoDB.Value = True) Then
        Printer.Print "Method: "; Tab(20); "DB  Factor: " & txtDB.Text
    ElseIf (rdoDDB.Value = True) Then
        Printer.Print "Method: "; Tab(20); "DDB"
    ElseIf (rdoMACRS.Value = True) Then
        Printer.Print "Method: "; Tab(20); "MACRS"
    End If
    
    iLinePosition = iLinePosition + LINE_SPACING
    Printer.CurrentY = iLinePosition
    Printer.Print "Initial Cost:"; Tab(20); txtInitialCost.Text
    
    If (txtSalvageValue.Enabled = True) Then
        iLinePosition = iLinePosition + LINE_SPACING
        Printer.CurrentY = iLinePosition
        Printer.Print "Salvage Value: "; Tab(20); txtSalvageValue.Text
    End If
    
    iLinePosition = iLinePosition + LINE_SPACING
    Printer.CurrentY = iLinePosition
    Printer.Print "Life: "; Tab(20); txtPeriods.Text

    
    iLinePosition = iLinePosition + LINE_SPACING
    
    i = 0
    
    iColPosition = 0
    Printer.CurrentX = iColPosition
    iLinePosition = iLinePosition + LINE_SPACING
    Printer.CurrentY = iLinePosition
    Printer.Print "Period";
    
    iColPosition = iColPosition + COLUMN_WIDTH
    Printer.CurrentX = iColPosition
    Printer.CurrentY = iLinePosition
    Printer.Print "Depreciation"
    
    iColPosition = iColPosition + COLUMN_WIDTH
    Printer.CurrentX = iColPosition
    Printer.CurrentY = iLinePosition
    Printer.Print "Book Value"
          
    
    iLinePosition = iLinePosition + LINE_SPACING
    
    While (i < UBound(iValues))
        iColPosition = 0
    
        Printer.CurrentX = iColPosition
        Printer.CurrentY = iLinePosition
        Printer.Print i & ""

    
        iColPosition = iColPosition + COLUMN_WIDTH
        Printer.CurrentX = iColPosition
        Printer.CurrentY = iLinePosition
        Printer.Print Format(iValues(i).iDepreciation, "0.00")

        iColPosition = iColPosition + COLUMN_WIDTH
        Printer.CurrentX = iColPosition
        Printer.CurrentY = iLinePosition
        Printer.Print Format(iValues(i).iBookValue, "0.00")
        
        
        iLinePosition = iLinePosition + LINE_SPACING
        i = i + 1
    Wend
    
    Printer.EndDoc
    
    
handleError:
    'the user pressed Cancel
    Exit Sub


End Sub


Private Sub butCalculate_Click()
  Dim iPercentage As Double
  
  If Not (IsNumeric(txtInitialCost.Text)) Then
    MsgBox "Please enter valid Initial Cost", vbInformation
  ElseIf Not (IsNumeric(txtPeriods.Text)) Then
    MsgBox "Please enter valid Life period", vbInformation
  Else

    If (rdoSL.Value = True) Then
      If Not (IsNumeric(txtSalvageValue.Text)) Then
        MsgBox "Please enter valid Salvage Value", vbInformation
      Else
        CalculateSL txtInitialCost.Text, txtSalvageValue.Text, txtPeriods.Text
      End If
    
    ElseIf (rdoSOYD.Value = True) Then
      If Not (IsNumeric(txtSalvageValue.Text)) Then
        MsgBox "Please enter valid Salvage Value", vbInformation
      Else
        CalculateSOYD txtInitialCost.Text, txtSalvageValue.Text, txtPeriods.Text
      End If
    
    ElseIf (rdoDB.Value = True) Then
'        MsgBox "DB not implemented yet"
'        CalculateDDB txtInitialCost.Text, txtSalvageValue.Text, txtPeriods.Text
'        iPercentage = InputBox("Enter fixed percentage", "Input")
        If (IsNumeric(txtDB.Text)) Then
            CalculateDB txtInitialCost.Text, 0, iPercentage, txtPeriods.Text
        Else
            MsgBox "Please enter valid Declining Balance factor", vbInformation
        
        End If

    
    ElseIf (rdoDDB.Value = True) Then
        CalculateDDB txtInitialCost.Text, 0, txtPeriods.Text
    
    ElseIf (rdoMACRS.Value = True) Then
        CalculateMACRS txtInitialCost.Text, 0, txtPeriods.Text
    
    End If
    
  End If
    
End Sub

Private Sub CalculateSL(iInitialCost As Currency, iSalvageValue As Currency, iPeriods As Integer)
    Dim i As Double
    
    ReDim iValues(iPeriods + 1) As DepreciationValues
    
    iValues(0).iBookValue = iInitialCost
    
    i = 1
    While (i < iPeriods + 1)
        iValues(i).iDepreciation = ((iInitialCost - iSalvageValue) / iPeriods)
        iValues(i).iBookValue = iInitialCost - (((iInitialCost - iSalvageValue) / iPeriods) * i)
        
'        MsgBox "value: " & iValues(i)
        i = i + 1
    Wend
    
    DisplayValues

End Sub

Private Sub CalculateSOYD(iInitialCost As Currency, iSalvageValue As Currency, iPeriods As Integer)
    Dim i As Double
    Dim iDigitsTotal As Integer
    
    
    i = 0
    iDigitsTotal = 0
    While (i < iPeriods + 1)
        iDigitsTotal = iDigitsTotal + i
        i = i + 1
    Wend
    
    ReDim iValues(iPeriods + 1) As DepreciationValues
    
    iValues(0).iBookValue = iInitialCost
    
    i = 1
    While (i < iPeriods + 1)
        iValues(i).iDepreciation = ((iInitialCost - iSalvageValue) / iDigitsTotal) * (iPeriods - i + 1)
        iValues(i).iBookValue = iValues(i - 1).iBookValue - iValues(i).iDepreciation
        
        i = i + 1
    Wend
    
    DisplayValues

End Sub

Private Sub CalculateDB(iInitialCost As Currency, iSalvageValue As Currency, iPercentage As Double, iPeriods As Integer)
    Dim i As Double
    Dim iFactor As Double
    
    ReDim iValues(iPeriods + 1) As DepreciationValues
    iFactor = txtDB.Text
    
    iValues(0).iBookValue = iInitialCost
    
    i = 1
    While (i < iPeriods + 1)
            iValues(i).iBookValue = iInitialCost * ((1 - (iFactor / iPeriods)) ^ i)
        
        iValues(i).iDepreciation = iValues(i - 1).iBookValue - iValues(i).iBookValue
        
        i = i + 1
    Wend
    
    DisplayValues

End Sub


Private Sub CalculateDDB(iInitialCost As Currency, iSalvageValue As Currency, iPeriods As Integer)
    Dim i As Double
    
    ReDim iValues(iPeriods + 1) As DepreciationValues
    
    iValues(0).iBookValue = iInitialCost
    
    i = 1
    While (i < iPeriods + 1)
'        If (i = iPeriods) Then
'            iValues(i).iBookValue = iSalvageValue
'        Else
            iValues(i).iBookValue = iInitialCost * ((1 - (2 / iPeriods)) ^ i)
'        End If
        
        
        iValues(i).iDepreciation = iValues(i - 1).iBookValue - iValues(i).iBookValue
        
        i = i + 1
    Wend
    
    DisplayValues

End Sub

Private Sub CalculateMACRS(iInitialCost As Currency, iSalvageValue As Currency, iPeriods As Integer)
    Dim i As Double
    Dim valuesDefined As Boolean
    
    Dim iMACRS(22) As Double
    
    valuesDefined = True
    If (iPeriods = 3) Then
        iMACRS(1) = 33.33
        iMACRS(2) = 44.45
        iMACRS(3) = 14.81
        iMACRS(4) = 7.41
    ElseIf (iPeriods = 5) Then
        iMACRS(1) = 20#
        iMACRS(2) = 32#
        iMACRS(3) = 19.2
        iMACRS(4) = 11.52
        iMACRS(5) = 11.52
        iMACRS(6) = 5.76
    ElseIf (iPeriods = 7) Then
        iMACRS(1) = 14.29
        iMACRS(2) = 24.49
        iMACRS(3) = 17.49
        iMACRS(4) = 12.49
        iMACRS(5) = 8.93
        iMACRS(6) = 8.92
        iMACRS(7) = 8.93
        iMACRS(8) = 4.46
    ElseIf (iPeriods = 10) Then
        iMACRS(1) = 10#
        iMACRS(2) = 18#
        iMACRS(3) = 14.4
        iMACRS(4) = 11.52
        iMACRS(5) = 9.22
        iMACRS(6) = 7.37
        iMACRS(7) = 6.55
        iMACRS(8) = 6.55
        iMACRS(9) = 6.55
        iMACRS(10) = 6.55
        iMACRS(11) = 3.28
    ElseIf (iPeriods = 15) Then
        iMACRS(1) = 5#
        iMACRS(2) = 9.5
        iMACRS(3) = 8.55
        iMACRS(4) = 7.7
        iMACRS(5) = 6.93
        iMACRS(6) = 6.23
        iMACRS(7) = 5.9
        iMACRS(8) = 5.9
        iMACRS(9) = 5.91
        iMACRS(10) = 5.9
        iMACRS(11) = 5.91
        iMACRS(12) = 5.9
        iMACRS(13) = 5.91
        iMACRS(14) = 5.9
        iMACRS(15) = 5.91
        iMACRS(16) = 2.95
    ElseIf (iPeriods = 20) Then
        iMACRS(1) = 3.75
        iMACRS(2) = 7.22
        iMACRS(3) = 6.68
        iMACRS(4) = 6.18
        iMACRS(5) = 5.71
        iMACRS(6) = 5.29
        iMACRS(7) = 4.89
        iMACRS(8) = 4.52
        iMACRS(9) = 4.46
        iMACRS(10) = 4.46
        iMACRS(11) = 4.46
        iMACRS(12) = 4.46
        iMACRS(13) = 4.46
        iMACRS(14) = 4.46
        iMACRS(15) = 4.46
        iMACRS(16) = 4.46
        iMACRS(17) = 4.46
        iMACRS(18) = 4.46
        iMACRS(19) = 4.46
        iMACRS(20) = 4.46
        iMACRS(21) = 2.23
    Else
        MsgBox "MACRS only defined for life of 3, 5, 7, 10, 15, and 20"
        valuesDefined = False

    End If
        
        
    If (valuesDefined) Then
    
        ReDim iValues(iPeriods + 2) As DepreciationValues
    
        iValues(0).iBookValue = iInitialCost
    
        i = 1
        While (i < iPeriods + 2)
            iValues(i).iDepreciation = iInitialCost * iMACRS(i) / 100#
        
            iValues(i).iBookValue = iValues(i - 1).iBookValue - iValues(i).iDepreciation
        
            i = i + 1
        Wend
    
        DisplayValues
    End If

End Sub


Private Sub DisplayValues()
    
    '*** Populate the text box ***'
    i = 0
    
    txtDisplay.Text = ""
    While (i < UBound(iValues))
        txtDisplay.Text = txtDisplay.Text & i & " " & iValues(i).iDepreciation & ", " & Format(iValues(i).iBookValue, "0.00") & vbCrLf
        i = i + 1
    Wend
    
    
    '*** Populate the grid ***'
    i = 0
    
    theGrid.Rows = 1
    While (i < UBound(iValues))
        
        theGrid.AddItem i & vbTab & Format(iValues(i).iDepreciation, "0.00") & vbTab & Format(iValues(i).iBookValue, "0.00")
        i = i + 1
    Wend
    
    
End Sub

Private Sub butGraph_Click()
    Dim i As Integer
    
    
  If (UBound(iValues) > 0) Then
    
    frmGraph.Width = 11700
    frmGraph.Height = 7200
    
    frmGraph.Show
    
    i = 0
    While (i < UBound(iValues))
        frmGraph.addValue iValues(i).iBookValue
        i = i + 1
    Wend
    
    frmGraph.drawGraph
  Else
    MsgBox "No values to display in graph", vbInformation
  End If
    
    
End Sub

Private Sub Form_Load()
    theGrid.FormatString = "Period              |Depreciation        |Book Value          "
    ReDim iValues(0) As DepreciationValues

    DISABLED_COLOR = RGB(192, 192, 192)
    ACTIVE_COLOR = RGB(255, 255, 255)

    txtDB.Enabled = False
    txtDB.BackColor = DISABLED_COLOR

End Sub

Private Sub rdoDB_Click()
    txtDB.Enabled = True
    txtDB.BackColor = ACTIVE_COLOR
    txtDB.SetFocus
    txtSalvageValue.Enabled = False
    txtSalvageValue.BackColor = DISABLED_COLOR

    
End Sub

Private Sub rdoDDB_Click()
    txtDB.Enabled = False
    txtDB.BackColor = DISABLED_COLOR
    txtSalvageValue.Enabled = False
    txtSalvageValue.BackColor = DISABLED_COLOR

End Sub

Private Sub rdoMACRS_Click()
    txtDB.Enabled = False
    txtDB.BackColor = DISABLED_COLOR
    txtSalvageValue.Enabled = False
    txtSalvageValue.BackColor = DISABLED_COLOR

End Sub

Private Sub rdoSL_Click()
    txtDB.Enabled = False
    txtDB.BackColor = DISABLED_COLOR
    txtSalvageValue.Enabled = True
    txtSalvageValue.BackColor = ACTIVE_COLOR

End Sub

Private Sub rdoSOYD_Click()
    txtDB.Enabled = False
    txtDB.BackColor = DISABLED_COLOR
    txtSalvageValue.Enabled = True
    txtSalvageValue.BackColor = ACTIVE_COLOR

End Sub
