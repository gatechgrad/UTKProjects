VERSION 5.00
Begin VB.Form frmGraph 
   BackColor       =   &H00E0E0E0&
   Caption         =   "Graph"
   ClientHeight    =   6465
   ClientLeft      =   60
   ClientTop       =   345
   ClientWidth     =   11355
   LinkTopic       =   "Form1"
   MDIChild        =   -1  'True
   ScaleHeight     =   6465
   ScaleWidth      =   11355
   Begin VB.CheckBox chkShowLine 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Show Lines"
      Height          =   375
      Left            =   480
      TabIndex        =   4
      Top             =   6000
      Value           =   1  'Checked
      Width           =   2295
   End
   Begin VB.CheckBox chkShowPoints 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Show Points"
      Height          =   375
      Left            =   480
      TabIndex        =   3
      Top             =   5640
      Value           =   1  'Checked
      Width           =   2295
   End
   Begin VB.CheckBox chkShowValues 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Show Values"
      Height          =   375
      Left            =   480
      TabIndex        =   2
      Top             =   5280
      Width           =   2175
   End
   Begin VB.PictureBox Picture1 
      Height          =   4935
      Left            =   120
      ScaleHeight     =   4875
      ScaleWidth      =   10275
      TabIndex        =   0
      Top             =   120
      Width           =   10335
      Begin VB.PictureBox theGraph 
         AutoRedraw      =   -1  'True
         BackColor       =   &H00FFFFFF&
         Height          =   4575
         Left            =   120
         ScaleHeight     =   4515
         ScaleWidth      =   9915
         TabIndex        =   1
         Top             =   120
         Width           =   9975
      End
   End
End
Attribute VB_Name = "frmGraph"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Option Explicit

Dim iValues() As Currency
Dim iCount As Integer
Dim iMax As Currency
Dim iMin As Currency




Public Sub addValue(i1 As Currency)
    iCount = iCount + 1
    ReDim Preserve iValues(iCount) As Currency
    
    iValues(iCount) = i1
    
    If (i1 > iMax) Then
        iMax = i1
    End If
    

End Sub

Public Sub drawGraph()
    Dim iBaseline As Long
    Dim i As Integer
    
    Dim iPrevX As Long
    Dim iPrevY As Long
    
    Dim iOffsetX As Long
    Dim iOffsetY As Long
    Dim iSpacing As Long
    Dim iRangePositive As Long
    
    
    Dim iMagnitude As Currency
    
  If (iCount > 0) Then
    iMagnitude = iMax
    
    
    iBaseline = (theGraph.Height / 2)
    iOffsetX = 500
    iOffsetY = 500
    iSpacing = 500
    iRangePositive = iBaseline - iOffsetY
    
    
    theGraph.Cls
    
    theGraph.Line (iOffsetX, iOffsetY)-(iOffsetX, iBaseline)
    theGraph.Line (iOffsetX, iBaseline)-((theGraph.Width - iOffsetX), iBaseline)
    
    
    i = 0
    While (i < iCount)
        theGraph.CurrentX = (i * iSpacing) + iOffsetX
        theGraph.CurrentY = iBaseline - (iRangePositive * iValues(i + 1) / iMagnitude)
        
        If (chkShowLine.Value = vbChecked) Then
        
            If (i > 0) Then
                theGraph.Line (iPrevX, iPrevY)-(theGraph.CurrentX, theGraph.CurrentY)
            End If
        
            iPrevX = theGraph.CurrentX
            iPrevY = theGraph.CurrentY
        End If
        
        If (chkShowPoints.Value = vbChecked) Then
            theGraph.FillStyle = vbFSSolid
            theGraph.FillColor = vbBlack
            theGraph.Circle (theGraph.CurrentX, theGraph.CurrentY), 20
        End If
        
        
        If (chkShowValues.Value = vbChecked) Then
            theGraph.Print "" & Format(iValues(i + 1), "0.00")
        End If
        
        i = i + 1
    Wend
  End If
    
End Sub



Private Sub chkShowLine_Click()
    drawGraph

End Sub

Private Sub chkShowPoints_Click()
    drawGraph
End Sub

Private Sub chkShowValues_Click()
    drawGraph
End Sub

Private Sub Form_Load()
    iCount = 0
    iMax = 0
    iMin = 0
End Sub
