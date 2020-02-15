VERSION 5.00
Object = "{831FDD16-0C5C-11D2-A9FC-0000F8754DA1}#2.0#0"; "mscomctl.ocx"
Object = "{F9043C88-F6F2-101A-A3C9-08002B2F49FB}#1.2#0"; "comdlg32.ocx"
Begin VB.MDIForm frmMain 
   BackColor       =   &H00808080&
   Caption         =   "ENGINEA:  Engineering Economic Analysis"
   ClientHeight    =   8970
   ClientLeft      =   165
   ClientTop       =   -4050
   ClientWidth     =   13530
   LinkTopic       =   "MDIForm1"
   StartUpPosition =   2  'CenterScreen
   Begin MSComDlg.CommonDialog CommonDialog1 
      Left            =   3000
      Top             =   5760
      _ExtentX        =   847
      _ExtentY        =   847
      _Version        =   393216
   End
   Begin MSComctlLib.ImageList ImageList1 
      Left            =   2280
      Top             =   5760
      _ExtentX        =   1005
      _ExtentY        =   1005
      BackColor       =   -2147483643
      ImageWidth      =   24
      ImageHeight     =   24
      MaskColor       =   12632256
      _Version        =   393216
      BeginProperty Images {2C247F25-8591-11D1-B16A-00C0F0283628} 
         NumListImages   =   10
         BeginProperty ListImage1 {2C247F27-8591-11D1-B16A-00C0F0283628} 
            Picture         =   "frmMain.frx":0000
            Key             =   ""
         EndProperty
         BeginProperty ListImage2 {2C247F27-8591-11D1-B16A-00C0F0283628} 
            Picture         =   "frmMain.frx":0712
            Key             =   ""
         EndProperty
         BeginProperty ListImage3 {2C247F27-8591-11D1-B16A-00C0F0283628} 
            Picture         =   "frmMain.frx":0E24
            Key             =   ""
         EndProperty
         BeginProperty ListImage4 {2C247F27-8591-11D1-B16A-00C0F0283628} 
            Picture         =   "frmMain.frx":1536
            Key             =   ""
         EndProperty
         BeginProperty ListImage5 {2C247F27-8591-11D1-B16A-00C0F0283628} 
            Picture         =   "frmMain.frx":1C48
            Key             =   ""
         EndProperty
         BeginProperty ListImage6 {2C247F27-8591-11D1-B16A-00C0F0283628} 
            Picture         =   "frmMain.frx":235A
            Key             =   ""
         EndProperty
         BeginProperty ListImage7 {2C247F27-8591-11D1-B16A-00C0F0283628} 
            Picture         =   "frmMain.frx":2A6C
            Key             =   ""
         EndProperty
         BeginProperty ListImage8 {2C247F27-8591-11D1-B16A-00C0F0283628} 
            Picture         =   "frmMain.frx":317E
            Key             =   ""
         EndProperty
         BeginProperty ListImage9 {2C247F27-8591-11D1-B16A-00C0F0283628} 
            Picture         =   "frmMain.frx":3890
            Key             =   ""
         EndProperty
         BeginProperty ListImage10 {2C247F27-8591-11D1-B16A-00C0F0283628} 
            Picture         =   "frmMain.frx":3FA2
            Key             =   ""
         EndProperty
      EndProperty
   End
   Begin MSComctlLib.Toolbar Toolbar1 
      Align           =   1  'Align Top
      Height          =   540
      Left            =   0
      TabIndex        =   0
      Top             =   0
      Width           =   13530
      _ExtentX        =   23865
      _ExtentY        =   953
      ButtonWidth     =   820
      ButtonHeight    =   794
      Appearance      =   1
      ImageList       =   "ImageList1"
      _Version        =   393216
      BeginProperty Buttons {66833FE8-8583-11D1-B16A-00C0F0283628} 
         NumButtons      =   11
         BeginProperty Button1 {66833FEA-8583-11D1-B16A-00C0F0283628} 
            Object.ToolTipText     =   "Open"
            ImageIndex      =   8
         EndProperty
         BeginProperty Button2 {66833FEA-8583-11D1-B16A-00C0F0283628} 
            Object.ToolTipText     =   "Save"
            ImageIndex      =   9
         EndProperty
         BeginProperty Button3 {66833FEA-8583-11D1-B16A-00C0F0283628} 
            Object.ToolTipText     =   "Print"
            ImageIndex      =   10
         EndProperty
         BeginProperty Button4 {66833FEA-8583-11D1-B16A-00C0F0283628} 
            Style           =   3
         EndProperty
         BeginProperty Button5 {66833FEA-8583-11D1-B16A-00C0F0283628} 
            Object.ToolTipText     =   "Cash Flow"
            ImageIndex      =   1
         EndProperty
         BeginProperty Button6 {66833FEA-8583-11D1-B16A-00C0F0283628} 
            Object.ToolTipText     =   "Replacement"
            ImageIndex      =   2
         EndProperty
         BeginProperty Button7 {66833FEA-8583-11D1-B16A-00C0F0283628} 
            Object.ToolTipText     =   "Depreciation"
            ImageIndex      =   3
         EndProperty
         BeginProperty Button8 {66833FEA-8583-11D1-B16A-00C0F0283628} 
            Object.ToolTipText     =   "Evaluation of Alternatives"
            ImageIndex      =   4
         EndProperty
         BeginProperty Button9 {66833FEA-8583-11D1-B16A-00C0F0283628} 
            Object.ToolTipText     =   "Loan / Mortgage"
            ImageIndex      =   5
         EndProperty
         BeginProperty Button10 {66833FEA-8583-11D1-B16A-00C0F0283628} 
            Object.ToolTipText     =   "Calculator"
            ImageIndex      =   6
         EndProperty
         BeginProperty Button11 {66833FEA-8583-11D1-B16A-00C0F0283628} 
            Object.Visible         =   0   'False
            Object.ToolTipText     =   "Tables"
            ImageIndex      =   7
         EndProperty
      EndProperty
   End
   Begin VB.Menu menuFile 
      Caption         =   "&File"
      Begin VB.Menu miOpen 
         Caption         =   "&Open"
      End
      Begin VB.Menu miSave 
         Caption         =   "&Save"
      End
      Begin VB.Menu miPrint 
         Caption         =   "&Print"
      End
      Begin VB.Menu miOptions 
         Caption         =   "&Options"
         Enabled         =   0   'False
         Visible         =   0   'False
      End
      Begin VB.Menu miExit 
         Caption         =   "&Exit"
      End
   End
   Begin VB.Menu menuAnalysis 
      Caption         =   "&Analysis"
      Begin VB.Menu miCashflow 
         Caption         =   "&Cash Flow"
      End
      Begin VB.Menu miReplacement 
         Caption         =   "&Replacement"
      End
      Begin VB.Menu miDepreciation 
         Caption         =   "&Depreciation"
      End
      Begin VB.Menu miEvalOfAlt 
         Caption         =   "&Evaluation of Alternatives"
         Begin VB.Menu miEvalRORPWAW 
            Caption         =   "ROR / PW / AW"
         End
         Begin VB.Menu miEvalBC 
            Caption         =   "Benefit / Cost"
         End
      End
      Begin VB.Menu miLoanMortgage 
         Caption         =   "&Loan / Mortgage"
      End
   End
   Begin VB.Menu menuFactorTable 
      Caption         =   "&Factor Table"
      Begin VB.Menu miCalculator 
         Caption         =   "Interest &Calculator"
      End
      Begin VB.Menu miTables 
         Caption         =   "&Tables"
         Visible         =   0   'False
      End
   End
   Begin VB.Menu window 
      Caption         =   "Window"
      WindowList      =   -1  'True
      Begin VB.Menu cascade 
         Caption         =   "Cascade"
      End
      Begin VB.Menu tileHorizontally 
         Caption         =   "Tile Horizontally"
      End
      Begin VB.Menu tileVertically 
         Caption         =   "Tile Vertically"
      End
      Begin VB.Menu arrangeIcons 
         Caption         =   "Arrange Icons"
      End
   End
   Begin VB.Menu menuHelp 
      Caption         =   "&Help"
      Begin VB.Menu miAbout 
         Caption         =   "&About"
      End
   End
End
Attribute VB_Name = "frmMain"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Option Explicit

Dim CashFlows() As frmCashFlow
Dim iCountCashFlow As Integer

Dim ReplacementForms() As frmReplacement
Dim iCountReplacement As Integer

Dim DepreciationForms() As frmDepreciation
Dim iCountDepreciation As Integer

Dim EvalAlternativesForms() As frmEvalAlternatives
Dim iCountEvalAlternatives As Integer



Private Sub MDIForm_Load()
    frmSplash.Show
    frmSplash.Refresh
    Constants.Sleep (2000)
    frmSplash.Hide
    Unload frmSplash

       Toolbar1.Buttons(8).Visible = False
       Toolbar1.Buttons(9).Visible = False
       
       
       
End Sub

Private Sub miAbout_Click()
    frmAbout.Show vbModal, Me
End Sub

Private Sub miEvalBC_Click()
    iCountEvalAlternatives = iCountEvalAlternatives + 1
    ReDim Preserve EvalAlternativesForms(iCountEvalAlternatives) As frmEvalAlternatives
    Set EvalAlternativesForms(iCountEvalAlternatives) = New frmEvalAlternatives
    EvalAlternativesForms(iCountEvalAlternatives).Caption = "Evaluation of Alternatives " & iCountEvalAlternatives
    EvalAlternativesForms(iCountEvalAlternatives).Tag = iCountEvalAlternatives
    EvalAlternativesForms(iCountEvalAlternatives).Show
    
    EvalAlternativesForms(iCountEvalAlternatives).Width = 11700
    EvalAlternativesForms(iCountEvalAlternatives).Height = 7200

    EvalAlternativesForms(iCountEvalAlternatives).Left = 16 * Screen.TwipsPerPixelX
    EvalAlternativesForms(iCountEvalAlternatives).Top = 16 * Screen.TwipsPerPixelY

End Sub

Private Sub miEvalRORPWAW_Click()
    frmEval.Width = 11700
    frmEval.Height = 7200

    frmEval.Left = 16 * Screen.TwipsPerPixelX
    frmEval.Top = 16 * Screen.TwipsPerPixelY
    
    
    frmEval.Show
End Sub

Private Sub miOpen_Click()
    Dim i As Integer
    Dim temp As String
    
    Dim strFileName As String
    
    CommonDialog1.Filter = "ENGINEA Files (*.eea)|*.eea|All Files (*.*)|*.*"
    CommonDialog1.ShowOpen
    
    strFileName = CommonDialog1.FileName
    
    
    If (Trim(strFileName) <> "") Then

        Open strFileName For Input As #1
        
        Line Input #1, temp
        Close #1
        If (temp = "[Cash Flow]") Then
            miCashflow_Click
            CashFlows(iCountCashFlow).miOpen strFileName
        
        ElseIf (temp = "[Replacement]") Then
            miReplacement_Click
            ReplacementForms(iCountReplacement).miOpen strFileName
        
        ElseIf (temp = "[Depreciation]") Then
            miDepreciation_Click
            DepreciationForms(iCountDepreciation).miOpen strFileName
        
        Else
            MsgBox "Not a valid ENGINEA file", vbInformation
        End If
        
    End If
        



End Sub
Private Sub miSave_Click()
    If Not (Me.ActiveForm Is Nothing) Then
        Me.ActiveForm.miSave
    Else
        MsgBox "No Active Window to Save", vbInformation
    End If
End Sub

Private Sub miPrint_Click()
    If Not (Me.ActiveForm Is Nothing) Then
        Me.ActiveForm.miPrint
    Else
        MsgBox "No Active Window to Print", vbInformation
    End If
End Sub

Private Sub miOptions_Click()
        Me.ActiveForm.miOptions
End Sub
Private Sub miExit_Click()
    Me.Hide
    End
End Sub


Private Sub miCashflow_Click()
    iCountCashFlow = iCountCashFlow + 1
    ReDim Preserve CashFlows(iCountCashFlow) As frmCashFlow
    Set CashFlows(iCountCashFlow) = New frmCashFlow
    CashFlows(iCountCashFlow).Caption = "Cash Flow Analysis " & iCountCashFlow
    CashFlows(iCountCashFlow).Tag = iCountCashFlow
    CashFlows(iCountCashFlow).Show
    
    CashFlows(iCountCashFlow).Width = 11700
    CashFlows(iCountCashFlow).Height = 7200
    
    CashFlows(iCountCashFlow).Left = 16 * Screen.TwipsPerPixelX
    CashFlows(iCountCashFlow).Top = 16 * Screen.TwipsPerPixelY
    
End Sub

Private Sub miReplacement_Click()
    iCountReplacement = iCountReplacement + 1
    ReDim Preserve ReplacementForms(iCountReplacement) As frmReplacement
    Set ReplacementForms(iCountReplacement) = New frmReplacement
    ReplacementForms(iCountReplacement).Caption = "Replacement Analysis " & iCountReplacement
    ReplacementForms(iCountReplacement).Tag = iCountReplacement
    ReplacementForms(iCountReplacement).Show
    
    
    ReplacementForms(iCountReplacement).Width = 736 * Screen.TwipsPerPixelX
    ReplacementForms(iCountReplacement).Height = 472 * Screen.TwipsPerPixelY
    
    ReplacementForms(iCountReplacement).Left = 16 * Screen.TwipsPerPixelX
    ReplacementForms(iCountReplacement).Top = 16 * Screen.TwipsPerPixelY


End Sub


Private Sub miDepreciation_Click()
    iCountDepreciation = iCountDepreciation + 1
    ReDim Preserve DepreciationForms(iCountDepreciation) As frmDepreciation
    Set DepreciationForms(iCountDepreciation) = New frmDepreciation
    DepreciationForms(iCountDepreciation).Caption = "Depreciation Analysis " & iCountDepreciation
    DepreciationForms(iCountDepreciation).Tag = iCountDepreciation
    DepreciationForms(iCountDepreciation).Show
    
    DepreciationForms(iCountDepreciation).Width = 8985
    DepreciationForms(iCountDepreciation).Height = 7035

    DepreciationForms(iCountDepreciation).Left = 16 * Screen.TwipsPerPixelX
    DepreciationForms(iCountDepreciation).Top = 16 * Screen.TwipsPerPixelY

End Sub


Private Sub miLoanMortgage_Click()
    MsgBox "Loan / Mortgage not implemented yet"

End Sub

Private Sub miCalculator_Click()
    
    frmCIFCal.Width = 5805
    frmCIFCal.Height = 5775
    
    frmCIFCal.Left = 16 * Screen.TwipsPerPixelX
    frmCIFCal.Top = 16 * Screen.TwipsPerPixelY
    
    
    frmCIFCal.Show
End Sub


Private Sub cascade_Click()
    Me.Arrange vbCascade
End Sub





Private Sub tileHorizontally_Click()
    Me.Arrange vbTileHorizontal
End Sub

Private Sub tileVertically_Click()
    Me.Arrange vbTileVertical

End Sub

Private Sub arrangeIcons_Click()
    Me.Arrange vbArrangeIcons
End Sub


Private Sub Toolbar1_ButtonClick(ByVal Button As MSComctlLib.Button)
    If (Button.Index = 5) Then
        miCashflow_Click
    ElseIf (Button.Index = 6) Then
        miReplacement_Click
    ElseIf (Button.Index = 7) Then
        miDepreciation_Click
    ElseIf (Button.Index = 8) Then
        miEvalBC_Click
    ElseIf (Button.Index = 9) Then
        miLoanMortgage_Click
    ElseIf (Button.Index = 10) Then
        miCalculator_Click
    ElseIf (Button.Index = 1) Then
        miOpen_Click
    ElseIf (Button.Index = 2) Then
        miSave_Click
    ElseIf (Button.Index = 3) Then
        miPrint_Click
        
    End If
End Sub
