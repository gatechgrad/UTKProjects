VERSION 5.00
Begin VB.Form frmAbout 
   Caption         =   "About"
   ClientHeight    =   4440
   ClientLeft      =   60
   ClientTop       =   345
   ClientWidth     =   6300
   LinkTopic       =   "Form1"
   ScaleHeight     =   4440
   ScaleWidth      =   6300
   StartUpPosition =   1  'CenterOwner
   Begin VB.CommandButton butOkay 
      Caption         =   "OK"
      Height          =   375
      Left            =   4320
      TabIndex        =   2
      Top             =   3840
      Width           =   1215
   End
   Begin VB.Label lblAbout 
      Height          =   2895
      Left            =   240
      TabIndex        =   1
      Top             =   600
      Width           =   5295
   End
   Begin VB.Label lblTitle 
      Caption         =   "ENGINEA:  Engineering Economic Analysis"
      Height          =   255
      Left            =   240
      TabIndex        =   0
      Top             =   240
      Width           =   3855
   End
End
Attribute VB_Name = "frmAbout"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Private Sub butOkay_Click()
    Unload Me
End Sub

Private Sub Form_Load()
    lblAbout = "Summer 2004 " & vbCrLf & _
               "Department of Industrial and Information Engineering " & vbCrLf & _
               "at the University of Tennessee in Knoxville " & vbCrLf & _
               vbCrLf & _
               "Software Development: " & vbCrLf & _
               "     Levi D. Smith" & vbCrLf & _
               vbCrLf & _
               "Software Design: " & vbCrLf & _
               "     Femi Omitaomu" & vbCrLf & _
               "     Levi D. Smith" & vbCrLf & _
               vbCrLf & _
               "Software Testing: " & vbCrLf & _
               ""
               
End Sub
